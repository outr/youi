package io.youi.server.dsl

import io.youi.http.HttpConnection

class ActionFilter(f: HttpConnection => HttpConnection) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Option[HttpConnection] = {
    Some(f(connection))
  }
}

object ActionFilter {
  def apply(f: HttpConnection => HttpConnection): ActionFilter = new ActionFilter(f)
}