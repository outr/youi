package io.youi.server.dsl

import io.youi.http.HttpConnection
import scribe.Execution.global

import scala.concurrent.Future

case class CombinedConnectionFilter(first: ConnectionFilter, second: ConnectionFilter) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Future[FilterResponse] = {
    first.filter(connection).flatMap {
      case FilterResponse.Continue(c) => second.filter(c)
      case FilterResponse.Stop(c) => Future.successful(FilterResponse.Stop(c))
    }
  }
}