package io.youi.server.dsl

import cats.effect.IO
import io.youi.http.HttpConnection

case class ListConnectionFilter(filters: List[ConnectionFilter]) extends ConnectionFilter {
  override def filter(connection: HttpConnection): IO[FilterResponse] = firstPath(connection, filters)

  private def firstPath(connection: HttpConnection,
                        filters: List[ConnectionFilter]): IO[FilterResponse] = if (filters.isEmpty) {
    IO.pure(FilterResponse.Stop(connection))
  } else {
    val filter = filters.head
    filter.filter(connection).flatMap {
      case r: FilterResponse.Continue => IO.pure(r)
      case r: FilterResponse.Stop => firstPath(r.connection, filters.tail)
    }
  }
}