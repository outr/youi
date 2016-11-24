package io.youi.example

import io.youi.communicate._

trait ExampleCommunicator extends Communicator {
  val url: ClientCommunicationCall[String] = clientRequest[String]

  // Send a String to the Server, reverse it, send it back
  val reverse: ServerCommunicationMethod[String, String] = serverMethod[String, String]
  // Ask the client for the browser's URL
//  val url = clientRequest[String]("urlImpl")
  val time: ServerCommunicationCall[Long] = serverRequest[Long]
}