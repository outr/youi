package io.youi.example

import scribe.Logging
import io.youi.http.{Connection, HttpConnection}
import io.youi.server.handler.HttpHandler

object WebSocketExample extends Connection with HttpHandler with Logging {
  receive.text.attach { message =>
    logger.info(s"Received message: $message.")
    send.text := message
  }

  override def handle(connection: HttpConnection): Unit = {
    connection.webSocketSupport = this
  }
}