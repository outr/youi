package io.youi.server

import java.io.File

import io.circe.{Decoder, Encoder}
import io.youi.http.{Content, FileContent, HttpConnection, HttpStatus, Method, StringContent, URLContent}
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

  implicit class HttpHandlerFilter(val handler: HttpHandler) extends ActionFilter(handler.handle)

  implicit class CachingManagerFilter(val caching: CachingManager) extends LastConnectionFilter(new ActionFilter(caching.handle))

  implicit class DeltasFilter(val deltas: List[Delta]) extends ActionFilter(processDeltas(_, deltas))

  implicit class DeltaFilter(delta: Delta) extends ActionFilter(processDeltas(_, List(delta)))

  implicit class StringFilter(val s: String) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = PathPart.take(connection, s)
  }

  private[server] def processDeltas(connection: HttpConnection, deltas: List[Delta] = Nil): Unit = {
    scribe.info(s"processing deltas: $deltas")
    connection.response.content match {
      case Some(content) => {
        val stream: StreamableHTML = content match {
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
      case None => {    // No content
        if (deltas.nonEmpty) {
          scribe.warn(s"Not content set for processing of deltas: $deltas")
        }
      }
    }
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

  def last(filters: ConnectionFilter*): ConnectionFilter = LastConnectionFilter(filters: _*)

  def respond(content: Content, status: HttpStatus = HttpStatus.OK): ContentHandler = {
    ContentHandler(content, status)
  }

  implicit def string2Content(value: String): Content = Content.string(value, ContentType.`text/plain`)
  implicit def xml2Content(value: Elem): Content = Content.xml(value, ContentType.`text/xml`)
}