package io.youi.server.handler

import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus}

case class ContentHandler(content: Content, status: HttpStatus) extends HttpHandler {
  override def handle(connection: HttpConnection): Unit = connection.update { response =>
    response.copy(status = status, content = Some(content))
  }
}