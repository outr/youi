package io.youi.example

//import io.youi.communicate.ClientWebSocketCommunication
import io.youi.net.URL
import org.scalajs.dom._

import scala.concurrent.Future

//object ClientExampleCommunicator extends ClientWebSocketCommunication(URL(s"ws://${window.location.host}/communicator")) {
//  override val interface: ExampleInterface = client[ExampleInterface]
//
//  def clientURL: String = window.location.href
//}

trait ClientExampleCommunication extends ExampleCommunication {
//  override def url: Future[String] = Future.successful(window.location.href)
}