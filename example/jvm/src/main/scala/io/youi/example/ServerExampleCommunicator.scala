package io.youi.example

import io.youi.communicate.{CommunicationImplementation, Communicator}

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object ServerExampleCommunicator extends CommunicationImplementation {
  val communicator = server[ExampleCommunicator]

  def time: Long = System.currentTimeMillis()

  def main(args: Array[String]): Unit = {
    val time = Await.result(communicator.time(), Duration.Inf)
    println(s"Time: $time")
  }
}