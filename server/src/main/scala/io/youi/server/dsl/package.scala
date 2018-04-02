package io.youi.server

import java.io.File

import io.circe.{Decoder, Encoder}
import io.youi.http.{Content, FileContent, HttpConnection, HttpStatus, Method, StringContent, URLContent}
import io.youi.net.{ContentType, IP, Path, URLMatcher}
import io.youi.server.handler._
import io.youi.server.rest.Restful
import io.youi.server.validation.{ValidationResult, Validator}
import io.youi.stream.{Delta, HTMLParser, Selector}

import scala.language.implicitConversions
import scala.xml.Elem

package object dsl {
  private[youi] val DeltaKey: String = "deltas"

  implicit class HttpConnectionConnectionFilter(val connection: HttpConnection) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = Some(connection)
  }

  implicit class ValidatorFilter(val validator: Validator) extends ConnectionFilter {
    private lazy val list = List(validator)

    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      ValidatorHttpHandler.validate(connection, list) match {
        case ValidationResult.Continue => Some(connection)
        case _ => None
      }
    }
  }

  implicit class MethodConnectionFilter(val method: Method) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = if (connection.request.method == method) {
      Some(connection)
    } else {
      None
    }
  }

  implicit class HttpHandlerFilter(val handler: HttpHandler) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      handler.handle(connection)
      Some(connection)
    }
  }

  implicit class CachingManagerFilter(val caching: CachingManager) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      caching.handle(connection)
      Some(connection)
    }
  }

  implicit class DeltasFilter(val deltas: List[Delta]) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      processDeltas(connection, deltas)
      Some(connection)
    }
  }

  implicit class StringFilter(val s: String) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = PathPart.take(connection, s)
  }

  private[server] def processDeltas(connection: HttpConnection, deltas: List[Delta] = Nil): Unit = {
    connection.response.content match {
      case Some(content) => {
        val stream = content match {
          case c: FileContent => HTMLParser.cache(c.file)
          case c: URLContent => HTMLParser.cache(c.url)
          case c: StringContent => HTMLParser.cache(c.value)
        }
        val deltasList = connection.store.getOrElse[List[Delta]](DeltaKey, Nil) ::: deltas

        if (deltasList.nonEmpty) {
          val selector = connection.request.url.param("selector").map(Selector.parse)
          val streamed = stream.stream(deltasList, selector)
          connection.update { response =>
            response.withContent(Content.string(streamed, content.contentType))
          }
        }
      }
      case None => // No content
    }
  }

  implicit class URLMatcherFilter(val matcher: URLMatcher) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      if (matcher.matches(connection.request.url)) {
        Some(connection)
      } else {
        None
      }
    }
  }

  case class ClassLoaderPath(directory: String = "", pathTransform: String => String = (s: String) => s) extends ConnectionFilter {
    private val dir = if (directory.endsWith("/")) {
      directory.substring(directory.length - 1)
    } else {
      directory
    }

    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      val path = pathTransform(connection.request.url.path.decoded)
      val resourcePath = s"$dir$path" match {
        case s if s.startsWith("/") => s.substring(1)
        case s => s
      }
      Option(getClass.getClassLoader.getResource(resourcePath))
        .map(url => new File(url.getFile))
        .filterNot(_.isDirectory)
        .map { file =>
          SenderHandler(Content.file(file)).handle(connection)
          connection
        }
    }
  }

  implicit def content2Filter(content: Content): ConnectionFilter = {
    new HttpHandlerFilter(ContentHandler(content, HttpStatus.OK))
  }

  implicit def restful[Request, Response](restful: Restful[Request, Response])
                                         (implicit decoder: Decoder[Request], encoder: Encoder[Response]): ConnectionFilter = {
    new HttpHandlerFilter(Restful(restful)(decoder, encoder))
  }

  implicit def path2AllowFilter(path: Path): ConnectionFilter = PathFilter(path)

  def filters(filters: ConnectionFilter*): ConnectionFilter = ListConnectionFilter(filters.toList)

  def allow(ips: IP*): ConnectionFilter = IPAddressFilter(allow = ips.toList)

  def allow(path: Path): ConnectionFilter = PathFilter(path)

  def respond(content: Content, status: HttpStatus = HttpStatus.OK): ContentHandler = {
    ContentHandler(content, status)
  }

  implicit def string2Content(value: String): Content = Content.string(value, ContentType.`text/plain`)
  implicit def xml2Content(value: Elem): Content = Content.xml(value, ContentType.`text/xml`)
}