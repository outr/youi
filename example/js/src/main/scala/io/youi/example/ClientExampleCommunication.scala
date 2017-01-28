package io.youi.example

import io.youi.comm.{ClientWebSocketCommunicator, Communication}
import org.scalajs.dom._
import io.youi.net.URL

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

trait ClientExampleCommunication extends ExampleCommunication {
  override def url: Future[String] = Future.successful(window.location.href)

  override def navigateTo(url: String, push: Boolean): Future[Unit] = Future.successful(if (push) {
    window.history.pushState(url, url, url)
  } else {
    window.location.href = url
  })
}

object ClientExampleCommunication extends ClientWebSocketCommunicator[ClientExampleCommunication](URL(s"ws://${window.location.host}/communicator")) {
  override protected def create(): ClientExampleCommunication = Communication.create[ClientExampleCommunication]
}