package io.youi.server.dsl

import cats.effect.IO
import io.youi.http.HttpConnection

case class LastConnectionFilter(filters: ConnectionFilter*) extends ConnectionFilter {
  override def filter(connection: HttpConnection): IO[FilterResponse] = IO {
    last(connection, filters: _*)
    FilterResponse.Continue(connection)
  }
}