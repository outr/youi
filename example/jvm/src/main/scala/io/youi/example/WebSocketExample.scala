package io.youi.example

import com.outr.scribe.Logging
import io.youi.http.{HttpConnection, WebSocketListener}
import io.youi.server.handler.HttpHandler

object WebSocketExample extends HttpHandler with WebSocketListener with Logging {
  receive.text.attach { message =>
    logger.info(s"Received message: $message.")
    send.text := message
  }

  override def handle(connection: HttpConnection): Unit = {
    connection.webSocketSupport = this
  }
}
