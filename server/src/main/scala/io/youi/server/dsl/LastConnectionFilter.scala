package io.youi.server.dsl
import io.youi.http.HttpConnection

import scala.concurrent.Future

case class LastConnectionFilter(filters: ConnectionFilter*) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Future[FilterResponse] = Future.successful {
    last(connection, filters: _*)
    FilterResponse.Continue(connection)
  }
}