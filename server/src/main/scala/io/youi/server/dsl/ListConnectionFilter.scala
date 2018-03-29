package io.youi.server.dsl

import io.youi.http.HttpConnection

case class ListConnectionFilter(filters: List[ConnectionFilter]) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Option[HttpConnection] = {
    filters.toStream.flatMap(_.filter(connection)).headOption
  }
}