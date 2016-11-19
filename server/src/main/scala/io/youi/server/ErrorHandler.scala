package io.youi.server

import io.youi.http.HttpConnection

trait ErrorHandler {
  def handle(connection: HttpConnection, t: Option[Throwable]): Unit
}
