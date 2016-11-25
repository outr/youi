package io.youi.communicate

trait ServerWebSocketCommunication extends Communication {
  override def send(messageId: Int, invocationId: Int, invocationType: InvocationType, message: Option[String]): Unit = {

  }
}
