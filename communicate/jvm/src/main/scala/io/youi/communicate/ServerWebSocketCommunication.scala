package io.youi.communicate

import io.youi.http.{HttpConnection, WebSocketListener}
import io.youi.server.handler.HttpHandler

trait ServerWebSocketCommunication extends WebSocketCommunication[WebSocketConnection] with HttpHandler {
  private var connections = Set.empty[WebSocketConnection]

  override protected def webSocketListener: WebSocketListener = context

  override def handle(connection: HttpConnection): Unit = synchronized {
    val c = new WebSocketConnection(this)
    connections += c
    connection.webSocketSupport = c
  }
}

class WebSocketConnection(communication: ServerWebSocketCommunication) extends WebSocketListener {
  receive.text.attach {
    case CommunicationMessage(message) => communication.contextualize(this) {
      communication.receive(message)
    }
  }
}