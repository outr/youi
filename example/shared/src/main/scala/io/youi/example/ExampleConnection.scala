package io.youi.example

import io.youi.communication.Connection

trait ExampleConnection extends Connection {
  val server: ServerExampleCommunication
  val client: ClientExampleCommunication
}