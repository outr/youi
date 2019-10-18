package io.youi.example

import scribe.Execution.global

class ServerConnection extends ExampleConnection {
  override val server: ServerExampleCommunication = implementation[ServerExampleCommunication, ServerExampleCommunicationImplementation]
  override val client: ClientExampleCommunication = interface[ClientExampleCommunication]
}