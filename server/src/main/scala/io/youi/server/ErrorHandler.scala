package io.youi.server

import cats.effect.IO
import io.youi.http.HttpConnection

trait ErrorHandler {
  def handle(connection: HttpConnection, t: Option[Throwable]): IO[HttpConnection]
}
