package org.hyperscala.ui.module

import java.net.URLEncoder

import com.outr.net.http.HttpHandler
import com.outr.net.http.content.{ContentType, StringContent}
import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.{HttpResponseStatus, HttpResponse}
import com.outr.net.http.session.Session
import org.hyperscala.module.{Interface, Module}
import org.hyperscala.html._
import org.hyperscala.javascript.dsl._
import org.hyperscala.web.{Website, Webpage}
import org.powerscala.Version

/**
 * WebFontLoader is a wrapper around TypeKit's WebFontLoader: https://github.com/typekit/webfontloader
 *
 * @author Matt Hicks <matt@outr.com>
 */
class WebFontLoader private(webpage: Webpage) {
  webpage.head.contents += new tag.Script(src = s"//ajax.googleapis.com/ajax/libs/webfont/${WebFontLoader.version}/webfont.js")

  private var loaded = Set.empty[String]

  def google(families: List[String]) = synchronized {
    val familyNames = families.toSet -- loaded
    if (familyNames.nonEmpty) {
      val js =
        s"""
           |WebFont.load({
           |  google: {
           |    families: [${familyNames.map(f => s"'$f'").mkString(", ")}]
           |  }
           |});
         """.stripMargin
      webpage.eval(js)
      loaded ++= familyNames
    }
  }

  def custom(family: String, style: String, weight: String, woff: String, aliases: List[String]) = synchronized {
    val key = s"$family;$style;$weight"
    if (!loaded.contains(key)) {
      WebFontLoader.synchronized {
        WebFontLoader.customFonts += key -> CustomFont(family, style, weight, woff, aliases)
      }
      val js =
        s"""
           |WebFont.load({
           |  custom: {
           |    families: ['$family'],
           |    urls: ['/webfontloader.css?family=${URLEncoder.encode(family, "UTF-8")}&style=${URLEncoder.encode(style, "UTF-8")}&weight=${URLEncoder.encode(weight, "UTF-8")}']
           |  }
           |});
         """.stripMargin
      webpage.eval(js)
      loaded += key
    }
  }

  def isLoaded(family: String) = loaded.contains(family)
}

case class CustomFont(family: String, style: String, weight: String, woff: String, aliases: List[String])

object WebFontLoader extends Module with HttpHandler {
  val name = "webfontloader"
  val version = Version(1, 5, 10)

  private var customFonts = Map.empty[String, CustomFont]

  override def dependencies: List[Interface] = super.dependencies

  override def init(website: Website) = {
    website.addHandler(this, "/webfontloader.css")
  }

  override def load(webpage: Webpage) = {
    apply(webpage)      // Instantiate the WebFontLoader for this webpage
  }

  def apply(webpage: Webpage) = webpage.store.getOrSet("webfontloader", new WebFontLoader(webpage))

  override def onReceive(request: HttpRequest, response: HttpResponse) = {
    val family = request.url.parameters.first("family")
    val style = request.url.parameters.first("style")
    val weight = request.url.parameters.first("weight")
    val key = s"$family;$style;$weight"
    val font = customFonts.getOrElse(key, throw new RuntimeException(s"Unable to find custom font: $family, $style, $weight."))
    val css =
      s"""
         |@font-face {
         |  font-family: '$family';
         |  font-style: $style;
         |  font-weight: $weight;
         |  src: ${font.aliases.map(s => s"local('$s')").mkString(", ")}, url(${font.woff}) format('woff');
         |}
       """.stripMargin
    response.copy(status = HttpResponseStatus.OK, content = StringContent(css, ContentType("text/css")))
  }
}