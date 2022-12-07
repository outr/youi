package io.youi.server.handler

import cats.effect.IO
import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus}

case class ContentHandler(content: Content, status: HttpStatus) extends HttpHandler {
  override def handle(connection: HttpConnection): IO[HttpConnection] = IO {
    connection.modify { response =>
      response.copy(status = status, content = Some(content))
    }
  }
}