package io.youi.example

import io.youi.communicate.{CommunicationImplementation, Communicator}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object ServerExampleCommunicator extends CommunicationImplementation {
  val communicator: ExampleCommunicator = server[ExampleCommunicator]

  def time: Long = System.currentTimeMillis()

  def reverse(text: String): String = text.reverse

  // TODO: should exist in ServerCommunicationImplementation
  override def send(messageId: Int, communicationId: Int, communicationType: Int, message: Option[String]): Unit = {
    println(s"send($messageId, $communicationId, $communicationType, $message)")
  }

  def main(args: Array[String]): Unit = {
    val time = Await.result(communicator.time(), Duration.Inf)
    println(s"Time: $time")

    val reverse = Await.result(communicator.reverse("Hello World!"), Duration.Inf)
    println(s"Reversed: $reverse")

    communicator.url()
  }
}