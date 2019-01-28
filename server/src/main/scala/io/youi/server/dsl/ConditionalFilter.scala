package io.youi.server.dsl

import io.youi.http.HttpConnection

import scala.concurrent.Future

class ConditionalFilter(f: HttpConnection => Boolean) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Future[FilterResponse] = Future.successful {
    if (f(connection)) {
      FilterResponse.Continue(connection)
    } else {
      FilterResponse.Stop(connection)
    }
  }
}
