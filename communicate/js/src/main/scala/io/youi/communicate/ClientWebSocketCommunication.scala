package io.youi.communicate

import io.youi.http.{WebSocketListener, WebSocketUtil}
import io.youi.net.URL
import org.scalajs.dom.WebSocket

abstract class ClientWebSocketCommunication(socketURL: URL) extends WebSocketCommunication with WebSocketListener {
  val webSocket: WebSocket = WebSocketUtil.connect(socketURL, this)
  receive.text.attach {
    case CommunicationMessage(message) => receive(message)
  }

  override protected def webSocketListener: WebSocketListener = this
}