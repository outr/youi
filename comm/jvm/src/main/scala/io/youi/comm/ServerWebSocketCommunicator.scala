package io.youi.comm

import io.youi.http.{HttpConnection, WebSocketListener}
import io.youi.server.handler.HttpHandler

trait ServerWebSocketCommunicator[C <: Communication] extends HttpHandler {
  protected def create(): C

  override def handle(connection: HttpConnection): Unit = synchronized {
    val listener = new WebSocketListener {}
    val communication = create()
    listener.receive.text.attach {
      case CommunicationMessage(message) => communication.comm.receive := message
    }
    communication.comm.send.attach { message =>
      listener.send.text := message.parsableString
    }
  }
}
