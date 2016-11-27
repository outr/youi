package io.youi.communicate

import io.youi.http.WebSocketListener

trait WebSocketCommunication[Context] extends Communication[Context] {
  protected def webSocketListener: WebSocketListener

  override def send(message: CommunicationMessage): Unit = {
    webSocketListener.send.text := message.parsableString
  }
}
