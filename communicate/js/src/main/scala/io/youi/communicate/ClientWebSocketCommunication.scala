package io.youi.communicate

import com.outr.props.Var
import io.youi.http.{WebSocketListener, WebSocketUtil}
import io.youi.net.URL
import org.scalajs.dom.WebSocket

abstract class ClientWebSocketCommunication(socketURL: URL) extends WebSocketCommunication[Unit] with WebSocketListener {
  val webSocket: Var[Option[WebSocket]] = Var(None)

  receive.text.attach {
    case CommunicationMessage(message) => receive(message)
  }

  def connect(): Unit = synchronized {
    disconnect()
    webSocket := Some(WebSocketUtil.connect(socketURL, this))
  }

  def disconnect(): Unit = synchronized {
    webSocket().foreach(_.close())
  }

  override protected def webSocketListener: WebSocketListener = this
}