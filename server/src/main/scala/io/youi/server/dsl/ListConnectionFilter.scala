package io.youi.server.dsl

import io.youi.http.HttpConnection

import scala.concurrent.Future
import scribe.Execution.global

case class ListConnectionFilter(filters: List[ConnectionFilter]) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Future[FilterResponse] = firstPath(connection, filters)

  private def firstPath(connection: HttpConnection,
                        filters: List[ConnectionFilter]): Future[FilterResponse] = if (filters.isEmpty) {
    Future.successful(FilterResponse.Stop(connection))
  } else {
    val filter = filters.head
    filter.filter(connection).flatMap {
      case r: FilterResponse.Continue => Future.successful(r)
      case r: FilterResponse.Stop => firstPath(r.connection, filters.tail)
    }
  }
}