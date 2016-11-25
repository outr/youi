package io.youi.example

import io.youi.communicate.{Communication, Interface, InvocationType}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object ServerExampleCommunicator extends Communication {
  override val interface: ExampleInterface = server[ExampleInterface]

  def time: Long = System.currentTimeMillis()

  def reverse(text: String): String = text.reverse

  // TODO: should exist in ServerCommunicationImplementation
  override def send(messageId: Int, invocationId: Int, invocationType: InvocationType, message: Option[String]): Unit = {
    println(s"send($messageId, $invocationId, $invocationType, $message)")
  }

  def main(args: Array[String]): Unit = {
    val time = Await.result(interface.time(), Duration.Inf)
    println(s"Time: $time")

    val reverse = Await.result(interface.reverse("Hello World!"), Duration.Inf)
    println(s"Reversed: $reverse")

    interface.url()
  }
}