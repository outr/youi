package io.youi.server.handler

import fabric.MergeType
import fabric.parse.JsonParser

import java.util.Locale
import java.util.concurrent.{ConcurrentHashMap, TimeUnit}
import java.util.regex.Matcher
import io.youi.http.content.Content
import io.youi.http.cookie.ResponseCookie
import io.youi.http.{Headers, HttpConnection}
import io.youi.net._
import fabric.rw._
import profig.{FileNameMatcher, Profig}
import scribe.Execution.global
import scribe.Priority

import scala.annotation.tailrec
import scala.concurrent.Future

/**
  * LanguageSupport adds simple multi-lingual support to HTML files
  *
  * Language files must be defined either in the resources path, or in the files path. They may include country for
  * greater control over the language information.  The files must be structured like the following:
  *  - language-LANGUAGE (ex. language-en)
  *  - language-LANGUAGE_COUNTRY (ex. language-en_US)
  *
  * The file type may be: json, properties, yml, yaml, hocon, xml, conf, or config.
  *
  * @param default the default locale to fall-back on
  */
class LanguageSupport(val default: Locale = Locale.ENGLISH, languagePath: Path = path"/language.json") extends HttpHandler {
  private lazy val emptyConfig = new Profig
  private val languageConfig = new ConcurrentHashMap[String, Profig]
  private val cookieName = "lang"

  override def handle(connection: HttpConnection): Future[HttpConnection] = if (connection.request.url.path == languagePath) {
    Future {
      val (c, l) = locales(connection)
      val config = firstConfig(l)
      c.modify { response =>
        response.withContent(Content.string(JsonParser.format(config.json), ContentType.`application/json`))
      }
    }
  } else {
    val updated = connection
      .response
      .content
      .filter(_.contentType == ContentType.`text/html`)
      .map(_.asString)
      .map(translate(connection, _))
      .getOrElse(connection)
    Future.successful(updated)
  }

  def locales(connection: HttpConnection): (HttpConnection, List[String]) = {
    val request = connection.request
    val paramLocales = request.url.param("lang").map(parseLocale).toList
    if (request.url.param("clearLanguageCache").contains("true")) {
      scribe.info(s"Clearing language cache...")
      clear()
    }
    val cookieLocales = request.cookies.find(_.name == cookieName).map(c => parseLocale(c.value)).toList
    val headerLocales = request.headers.get(Headers.Request.`Accept-Language`).flatMap(_.split(',')).map(parseLocale)
    val locales = (paramLocales ::: cookieLocales ::: headerLocales ::: List(default)).distinct

    // Param override - set a cookie to store it
    val c = if (paramLocales.nonEmpty) {
      connection.modify { response =>
        val localesString = paramLocales.map {
          case l if l.getCountry.nonEmpty => s"${l.getLanguage}-${l.getCountry}"
          case l => l.getLanguage
        }.mkString(", ")
        val OneYear = TimeUnit.SECONDS.convert(365, TimeUnit.DAYS)
        response.withHeader(Headers.Response.`Set-Cookie`(ResponseCookie(cookieName, localesString, maxAge = Some(OneYear))))
      }
    } else {
      connection
    }

    (c, locales.map(_.toString))
  }

  def translate(connection: HttpConnection, html: String): HttpConnection = {
    val (c, l) = locales(connection)
    val updatedHTML = translate(l, html)

    c.modify { response =>
      response.withContent(Content.string(updatedHTML, ContentType.`text/html`))
    }
  }

  def translate(locales: List[String], html: String): String = {
    val config = firstConfig(locales)

    val matcher = """[{]([a-zA-Z0-9._-]+?)[}]""".r
    matcher.replaceAllIn(html, m => {
      config(m.group(1)).opt[String] match {
        case Some(replacement) => Matcher.quoteReplacement(replacement)
        case None => s"{${m.group(1)}}"
      }
    })
  }

  /**
    * Clears the configuration cache so it will reload on the next request
    */
  def clear(): Unit = languageConfig.clear()

  override def priority: Priority = Priority.Fallthrough

  def firstConfig(localeStrings: List[String]): Profig = if (localeStrings.isEmpty) {
    emptyConfig
  } else {
    val locale = localeStrings.head
    Option(languageConfig.get(locale)) match {
      case Some(config) => config
      case None => {
        val config = new Profig
        config.initConfiguration(fileNameMatcher = new FileNameMatcher {
          override def matches(prefix: String, extension: String): Option[MergeType] = {
            if (FileNameMatcher.DefaultExtensions.contains(extension) && prefix == s"language-$locale") {
              Some(MergeType.Overwrite)
            } else {
              None
            }
          }
        })
        if (config.json.nonEmpty) {
          languageConfig.put(locale, config)
          config
        } else {
          firstConfig(localeStrings.tail)
        }
      }
    }
  }

  @tailrec
  private def parseLocale(s: String): Locale = if (s.contains(';')) {
    parseLocale(s.substring(0, s.indexOf(';')))
  } else {
    val value = s.trim
    val split = value.indexOf('-') match {
      case -1 => value.indexOf('_')
      case i => i
    }
    if (split != -1) {
      val language = value.substring(0, split).toLowerCase
      val country = value.substring(split + 1).toUpperCase
      new Locale(language, country)
    } else {
      new Locale(value.toLowerCase)
    }
  }
}