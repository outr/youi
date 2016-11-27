package io.youi.communicate

import com.outr.props.Channel
import io.youi.http.{HttpConnection, WebSocketListener}
import io.youi.server.handler.HttpHandler

trait ServerWebSocketCommunication extends WebSocketCommunication[WebSocketConnection] with HttpHandler {
  val connected: Channel[WebSocketConnection] = Channel()
  val disconnected: Channel[WebSocketConnection] = Channel()

  private var _connections = Set.empty[WebSocketConnection]
  def connections: Set[WebSocketConnection] = _connections

  override protected def webSocketListener: WebSocketListener = context

  override def handle(connection: HttpConnection): Unit = synchronized {
    val c = new WebSocketConnection(this)
    c.connected.attachAndFire { b =>
      if (b) {
        connected := c
      } else {
        disconnected := c
      }
    }
    _connections += c
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