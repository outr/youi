package io.youi.example
import scala.concurrent.Future

trait ServerSimpleJVMCommunication extends SimpleJVMCommunication {
  override def reverse(text: String): Future[String] = Future.successful(text.reverse)
}