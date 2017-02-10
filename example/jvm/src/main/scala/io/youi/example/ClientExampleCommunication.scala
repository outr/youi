package io.youi.example

import scala.concurrent.Future

trait ClientExampleCommunication extends ExampleCommunication {
  override def url: Future[String] = Future.successful("not implemented")

  override def navigateTo(url: String, push: Boolean): Future[Unit] = Future.successful(())

  override def show(message: String): Future[Unit] = Future.successful(())
}