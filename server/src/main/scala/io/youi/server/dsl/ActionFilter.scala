package io.youi.server.dsl

import cats.effect.IO
import io.youi.http.HttpConnection

class ActionFilter(f: HttpConnection => IO[HttpConnection]) extends ConnectionFilter {
  override def filter(connection: HttpConnection): IO[FilterResponse] = {
    f(connection).map(FilterResponse.Continue)
  }
}

object ActionFilter {
  def apply(f: HttpConnection => IO[HttpConnection]): ActionFilter = new ActionFilter(f)
}