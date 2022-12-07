package io.youi.server.dsl

import cats.effect.IO
import io.youi.http.HttpConnection

class ConditionalFilter(f: HttpConnection => Boolean) extends ConnectionFilter {
  override def filter(connection: HttpConnection): IO[FilterResponse] = IO {
    if (f(connection)) {
      FilterResponse.Continue(connection)
    } else {
      FilterResponse.Stop(connection)
    }
  }
}
