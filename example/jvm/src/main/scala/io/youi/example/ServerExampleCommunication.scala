package io.youi.example

import io.youi.comm.{Communication, ServerWebSocketCommunicator}
import io.youi.http.Connection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ServerExampleCommunication extends ExampleCommunication {
  override def reverse(value: String): Future[String] = Future(value.reverse)
  override def time: Future[Long] = Future(System.currentTimeMillis())
}

object ServerExampleCommunication extends ServerWebSocketCommunicator[ServerExampleCommunication] {
  override protected def create(connection: Connection): ServerExampleCommunication = Communication.create[ServerExampleCommunication](connection)
}