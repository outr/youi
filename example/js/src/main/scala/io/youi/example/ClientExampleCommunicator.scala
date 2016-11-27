package io.youi.example

import io.youi.communicate.ClientWebSocketCommunication
import io.youi.net.URL
import org.scalajs.dom._

object ClientExampleCommunicator extends ClientWebSocketCommunication(URL(s"ws://${window.location.host}/communicator")) {
  override val interface: ExampleInterface = client[ExampleInterface]

  def url: String = window.location.href
}