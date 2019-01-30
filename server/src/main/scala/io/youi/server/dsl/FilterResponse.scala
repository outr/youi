package io.youi.server.dsl

import io.youi.http.HttpConnection

sealed trait FilterResponse {
  def connection: HttpConnection
}

object FilterResponse {
  case class Continue(connection: HttpConnection) extends FilterResponse
  case class Stop(connection: HttpConnection) extends FilterResponse
}