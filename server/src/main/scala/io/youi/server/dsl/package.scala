package io.youi.server

import io.youi.http.{Content, HttpConnection, Method, Status}
import io.youi.net.{ContentType, IP, Path}
import io.youi.server.handler.{ContentHandler, HttpHandler}

import scala.language.implicitConversions
import scala.util.matching.Regex
import scala.xml.Elem

package object dsl {
  implicit class HttpConnectionConnectionFilter(val connection: HttpConnection) extends ConnectionFilter {
    override def filter(connection: HttpConnection): Option[HttpConnection] = Some(connection)
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

  implicit def path2AllowFilter(path: Path): ConnectionFilter = PathFilter(path)

  def filters(filters: ConnectionFilter*): ConnectionFilter = ListConnectionFilter(filters.toList)

  def allow(ips: IP*): ConnectionFilter = IPAddressFilter(allow = ips.toList)

  def allow(path: Regex): ConnectionFilter = PathRegexFilter(path)
  def allow(path: Path): ConnectionFilter = PathFilter(path)

  def respond(content: Content, status: Status = Status.OK): ContentHandler = {
    ContentHandler(content, status)
  }

  implicit def string2Content(value: String): Content = Content.string(value, ContentType.`text/plain`)
  implicit def xml2Content(value: Elem): Content = Content.xml(value, ContentType.`text/xml`)
}