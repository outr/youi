package io.youi.server.dsl

import cats.effect.IO
import io.youi.http.HttpConnection

case class CombinedConnectionFilter(first: ConnectionFilter, second: ConnectionFilter) extends ConnectionFilter {
  override def filter(connection: HttpConnection): IO[FilterResponse] = {
    first.filter(connection).flatMap {
      case FilterResponse.Continue(c) => second.filter(c)
      case FilterResponse.Stop(c) => IO.pure(FilterResponse.Stop(c))
    }
  }
}