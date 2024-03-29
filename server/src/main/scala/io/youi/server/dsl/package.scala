package io.youi.server

import cats.effect.IO

import java.io.File
import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpMethod, HttpStatus}
import io.youi.net.{ContentType, IP, Path, URLMatcher}
import io.youi.server.handler._
import io.youi.server.rest.Restful
import io.youi.server.validation.{ValidationResult, Validator}
import io.youi.stream.delta.Delta
import fabric.rw._

import scala.language.implicitConversions

package object dsl {
  private[youi] val DeltaKey: String = "deltas"

  implicit class ValidatorFilter(val validator: Validator) extends ConnectionFilter {
    private lazy val list = List(validator)

    override def filter(connection: HttpConnection): IO[FilterResponse] = {
      ValidatorHttpHandler.validate(connection, list).map {
        case ValidationResult.Continue(c) => FilterResponse.Continue(c)
        case vr => FilterResponse.Stop(vr.connection)
      }
    }
  }

  implicit class MethodConnectionFilter(val method: HttpMethod) extends ConditionalFilter(_.request.method == method)

  implicit def handler2Filter(handler: HttpHandler): ConnectionFilter = ActionFilter { connection =>
    if (PathPart.fulfilled(connection)) {
      handler.handle(connection)
    } else {
      IO.pure(connection)
    }
  }

  implicit class CachingManagerFilter(val caching: CachingManager) extends LastConnectionFilter(handler2Filter(caching))

  implicit class DeltasFilter(val deltas: List[Delta]) extends ActionFilter(connection => IO {
    connection.deltas ++= deltas
    connection
  })

  implicit class DeltaFilter(delta: Delta) extends ActionFilter(connection => IO {
    connection.deltas += delta
    connection
  })

  implicit class StringFilter(val s: String) extends ConnectionFilter {
    override def filter(connection: HttpConnection): IO[FilterResponse] = IO {
      PathPart.take(connection, s) match {
        case Some(c) => FilterResponse.Continue(c)
        case None => FilterResponse.Stop(connection)
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

    override def filter(connection: HttpConnection): IO[FilterResponse] = {
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
        }.map(_.map(FilterResponse.Continue)).getOrElse(IO.pure(FilterResponse.Stop(connection)))
    }
  }

  implicit def content2Filter(content: Content): ConnectionFilter = handler2Filter(ContentHandler(content, HttpStatus.OK))

  implicit def restful[Request, Response](restful: Restful[Request, Response])
                                         (implicit writer: Writer[Request], reader: Reader[Response]): ConnectionFilter = {
    handler2Filter(Restful(restful)(writer, reader))
  }

  implicit def path2AllowFilter(path: Path): ConnectionFilter = PathFilter(path)

  def filters(filters: ConnectionFilter*): ConnectionFilter = ListConnectionFilter(filters.toList)

  def allow(ips: IP*): ConnectionFilter = IPAddressFilter(allow = ips.toList)

  def allow(path: Path): ConnectionFilter = PathFilter(path)

  def last(filters: ConnectionFilter*): ConnectionFilter = LastConnectionFilter(filters: _*)

  def respond(content: Content, status: HttpStatus = HttpStatus.OK): ContentHandler = {
    ContentHandler(content, status)
  }

  def redirect(path: Path): ConnectionFilter = new ConnectionFilter {
    override def filter(connection: HttpConnection): IO[FilterResponse] = IO.pure {
      FilterResponse.Continue(HttpHandler.redirect(connection, path.encoded))
    }
  }

  implicit def string2Content(value: String): Content = Content.string(value, ContentType.`text/plain`)
}