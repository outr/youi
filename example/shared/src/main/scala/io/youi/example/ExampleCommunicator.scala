package io.youi.example

import io.youi.communicate.Communicator

trait ExampleCommunicator extends Communicator {
  // Send a String to the Server, reverse it, send it back
//  val reverse = serverMethod[String, String]("reverseImpl")
  // Ask the client for the browser's URL
//  val url = clientRequest[String]("urlImpl")
  val time = serverRequest[Long]("serverTime")
}