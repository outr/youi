package io.youi.server.dsl

import io.youi.http.HttpConnection

import scala.concurrent.Future

class ActionFilter(f: HttpConnection => HttpConnection) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Future[FilterResponse] = {
    Future.successful(FilterResponse.Continue(f(connection)))
  }
}

object ActionFilter {
  def apply(f: HttpConnection => HttpConnection): ActionFilter = new ActionFilter(f)
}