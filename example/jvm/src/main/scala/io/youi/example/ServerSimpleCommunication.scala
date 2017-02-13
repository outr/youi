package io.youi.example
import scala.concurrent.Future

trait ServerSimpleCommunication extends SimpleCommunication {
  override def reverse(text: String): Future[String] = Future.successful(text.reverse)
}