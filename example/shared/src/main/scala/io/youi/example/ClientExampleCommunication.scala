package io.youi.example

import scala.concurrent.Future

trait ClientExampleCommunication {
  def url(): Future[String]
  def navigateTo(url: String, push: Boolean): Future[Unit]
  def show(message: String): Future[Unit]
}