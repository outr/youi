package io.youi.example

import io.youi.communicate.ServerWebSocketCommunication

object ServerExampleCommunicator extends ServerWebSocketCommunication {
  override val interface: ExampleInterface = server[ExampleInterface]

  def time: Long = System.currentTimeMillis()

  def reverse(text: String): String = text.reverse
}