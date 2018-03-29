package io.youi.server.dsl

import io.youi.http.HttpConnection
import io.youi.net.Path

case class PathFilter(path: Path) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Option[HttpConnection] = {
    if (path.decoded == connection.request.url.path.decoded) {
      Some(connection)
    } else {
      None
    }
  }
}