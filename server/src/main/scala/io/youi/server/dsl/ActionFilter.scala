package io.youi.server.dsl

import io.youi.http.HttpConnection

import scala.concurrent.Future
import scribe.Execution.global

class ActionFilter(f: HttpConnection => Future[HttpConnection]) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Future[FilterResponse] = {
    f(connection).map(FilterResponse.Continue)
  }
}

object ActionFilter {
  def apply(f: HttpConnection => Future[HttpConnection]): ActionFilter = new ActionFilter(f)
}