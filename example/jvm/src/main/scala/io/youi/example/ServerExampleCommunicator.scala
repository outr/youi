package io.youi.example

import io.youi.communicate.Communicator

object ServerExampleCommunicator {
  val communicator = Communicator.server[ExampleCommunicator]

  def main(args: Array[String]): Unit = {
    println(s"Time: ${communicator.time()}")
  }
}