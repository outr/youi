package io.youi.server.dsl

import io.youi.http.HttpConnection

import scala.concurrent.Future

case class ListConnectionFilter(filters: List[ConnectionFilter]) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Future[FilterResponse] = {
    ConnectionFilter.recurse(connection, filters)
  }
}