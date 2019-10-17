package io.youi.example

import scala.concurrent.Future

trait ServerExampleCommunication {
  def reverse(value: String): Future[String]
  def time: Future[Long]
  def counter: Future[Int]
  def broadcast(message: String): Future[Unit]
  def logIn(username: String, password: String): Future[Option[String]]
}