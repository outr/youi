package io.youi.example

import scribe.Execution.global

object ClientConnection extends ExampleConnection {
  override val server: ServerExampleCommunication = interface[ServerExampleCommunication]
  override val client: ClientExampleCommunication = implementation[ClientExampleCommunication, ClientExampleCommunicationImplementation]
}