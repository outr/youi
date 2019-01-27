package io.youi.server.handler

import io.youi.http.content.Content
import io.youi.http.{HttpConnection, HttpStatus}

import scala.concurrent.Future

case class ContentHandler(content: Content, status: HttpStatus) extends HttpHandler {
  override def handle(connection: HttpConnection): Future[HttpConnection] = Future.successful {
    connection.modify { response =>
      response.copy(status = status, content = Some(content))
    }
  }
}