package io.youi.server

import java.io.File

import io.circe.{Decoder, Encoder}
import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus, Method}
import io.youi.net.{ContentType, IP, Path, URLMatcher}
import io.youi.server.handler._
import io.youi.server.rest.Restful
import io.youi.server.validation.{ValidationResult, Validator}
import io.youi.stream.{Delta, HTMLParser, Selector, StreamableHTML}

import scala.language.implicitConversions
import scala.xml.Elem

package object dsl {
  private[youi] val DeltaKey: String = "deltas"

  implicit class HttpConnectionConnectionFilter(val connection: HttpConnection) extends ActionFilter(_ => ())

  implicit class ValidatorFilter(val validator: Validator) extends ConnectionFilter {
    private lazy val list = List(validator)

    override def filter(connection: HttpConnection): Option[HttpConnection] = {
      ValidatorHttpHandler.validate(connection, list) match {
        case ValidationResult.Continue => Some(connection)
        case _ => None
      }
    }
  }

  implicit class MethodConnectionFilter(val method: Method) extends ConditionalFilter(_.request.method == method)

  implicit def handler2Filter(handler: HttpHandler): ConnectionFilter = ActionFilter { connection =>
    if (PathPart.fulfilled(connection)) {
      handler.handle(connection)
    }
  }

  implicit class CachingManagerFilter(val caching: CachingManager) extends LastConnectionFilter(new ActionFilter(caching.handle))

  implicit class DeltasFilter(val deltas: List[Delta]) extends ActionFilter(_.deltas += deltas)

  implicit class DeltaFilter(delta: Delta) extends ActionFilter(_.deltas += delta)

  implicit class StringFilter(val s: String) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = PathPart.take(connection, s)
  }

  implicit class URLMatcherFilter(val matcher: URLMatcher) extends ConditionalFilter(c => matcher.matches(c.request.url))

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
        .filter(_.isFile)
        .map { file =>
          SenderHandler(Content.file(file)).handle(connection)
          connection
        }
    }
  }

  implicit def content2Filter(content: Content): ConnectionFilter = handler2Filter(ContentHandler(content, HttpStatus.OK))

  implicit def restful[Request, Response](restful: Restful[Request, Response])
                                         (implicit decoder: Decoder[Request], encoder: Encoder[Response]): ConnectionFilter = {
    handler2Filter(Restful(restful)(decoder, encoder))
  }

  implicit def path2AllowFilter(path: Path): ConnectionFilter = PathFilter(path)

  def filters(filters: ConnectionFilter*): ConnectionFilter = ListConnectionFilter(filters.toList)

  def allow(ips: IP*): ConnectionFilter = IPAddressFilter(allow = ips.toList)

  def allow(path: Path): ConnectionFilter = PathFilter(path)

  def last(filters: ConnectionFilter*): ConnectionFilter = LastConnectionFilter(filters: _*)

  def respond(content: Content, status: HttpStatus = HttpStatus.OK): ContentHandler = {
    ContentHandler(content, status)
  }

  implicit def string2Content(value: String): Content = Content.string(value, ContentType.`text/plain`)
  implicit def xml2Content(value: Elem): Content = Content.xml(value, ContentType.`text/xml`)
}