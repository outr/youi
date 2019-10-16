package io.youi.example

object ClientConnection extends ExampleConnection {
  override val server: ServerExampleCommunication = apply[ServerExampleCommunication]("server")
  override val client: ClientExampleCommunication = apply[ClientExampleCommunication, ClientExampleCommunicationImplementation]("client")
}