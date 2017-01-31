package io.youi.example

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait ServerExampleCommunication extends ExampleCommunication {
  override def reverse(value: String): Future[String] = Future(value.reverse)
  override def time: Future[Long] = Future(System.currentTimeMillis())
}