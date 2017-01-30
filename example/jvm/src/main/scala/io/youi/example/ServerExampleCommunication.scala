package io.youi.example

import io.youi.comm.{Communication, ServerWebSocketCommunicator}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ServerExampleCommunication extends ExampleCommunication {
//  override val randomId: Long = Random.nextLong()
  override def reverse(value: String): Future[String] = Future(value.reverse)
  override def time: Future[Long] = Future(System.currentTimeMillis())
}

object ServerExampleCommunication extends ServerWebSocketCommunicator[ServerExampleCommunication] {
  override protected def create(): ServerExampleCommunication = Communication.create[ServerExampleCommunication]
}