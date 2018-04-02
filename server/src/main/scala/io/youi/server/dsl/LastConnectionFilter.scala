package io.youi.server.dsl
import io.youi.http.HttpConnection

case class LastConnectionFilter(filters: ConnectionFilter*) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Option[HttpConnection] = {
    last(connection, filters: _*)
    Some(connection)
  }
}