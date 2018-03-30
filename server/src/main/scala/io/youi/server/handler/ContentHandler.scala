package io.youi.server.handler

import io.youi.http.{Content, HttpConnection, Status}
import io.youi.server.dsl

case class ContentHandler(content: Content, status: Status) extends HttpHandler {
  override def handle(connection: HttpConnection): Unit = {
    connection.update { response =>
      response.copy(status = status, content = Some(content))
    }
    dsl.processDeltas(connection)
  }
}