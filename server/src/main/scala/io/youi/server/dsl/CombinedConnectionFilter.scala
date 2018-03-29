package io.youi.server.dsl

import io.youi.http.HttpConnection

case class CombinedConnectionFilter(first: ConnectionFilter, second: ConnectionFilter) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Option[HttpConnection] = {
    first.filter(connection).flatMap(second.filter)
  }
}