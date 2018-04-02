package io.youi.server.dsl

import io.youi.http.HttpConnection

class ConditionalFilter(f: HttpConnection => Boolean) extends ConnectionFilter {
  override def filter(connection: HttpConnection): Option[HttpConnection] = Some(connection).filter(f)
}
