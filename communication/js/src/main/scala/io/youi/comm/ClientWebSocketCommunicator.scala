package io.youi.comm

import com.outr.reactify.Var
import io.youi.http.{WebSocketListener, WebSocketUtil}
import io.youi.net.URL
import org.scalajs.dom.WebSocket

abstract class ClientWebSocketCommunicator[C <: Communication](socketURL: URL) extends WebSocketListener {
  val webSocket: Var[Option[WebSocket]] = Var(None)

  val communication: C = create()
  receive.text.attach {
    case CommunicationMessage(message) => communication.comm.receive := message
  }
  communication.comm.send.attach { message =>
    send.text := message.parsableString
  }

  protected def create(): C

  def connect(): Unit = synchronized {
    disconnect()
    webSocket := Some(WebSocketUtil.connect(socketURL, this))
  }

  def disconnect(): Unit = synchronized {
    webSocket().foreach { ws =>
      if (ws.readyState == WebSocket.OPEN) {
        ws.close()
      }
      webSocket := None
    }
  }

  override def close(): Unit = {
    super.close()

    disconnect()
  }
}
