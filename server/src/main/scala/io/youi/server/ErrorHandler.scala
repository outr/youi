package io.youi.server

import io.youi.http.HttpConnection

import scala.concurrent.Future

trait ErrorHandler {
  def handle(connection: HttpConnection, t: Option[Throwable]): Future[HttpConnection]
}
