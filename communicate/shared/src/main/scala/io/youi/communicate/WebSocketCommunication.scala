package io.youi.communicate

import io.youi.http.WebSocketListener

trait WebSocketCommunication extends Communication {
  protected def webSocketListener: WebSocketListener

  override def send(message: CommunicationMessage): Unit = {
    webSocketListener.send.text := message.parsableString
  }
}
