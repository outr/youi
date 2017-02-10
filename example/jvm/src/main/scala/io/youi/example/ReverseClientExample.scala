package io.youi.example

import io.youi.communication.Communication
import io.youi.net.URL
import io.youi.server.WebSocketClient

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object ReverseClientExample {
  val connection: WebSocketClient = new WebSocketClient(URL("http://localhost:8080/communication"))
  val comm: ClientExampleCommunication = Communication.create[ClientExampleCommunication](connection)

  def main(args: Array[String]): Unit = {
    connection.connect()
    try {
      val future = comm.reverse("This is a test!")
      val result = Await.result(future, 5.seconds)
      scribe.info(s"Receive: $result")
    } finally {
      connection.dispose()
    }
  }
}