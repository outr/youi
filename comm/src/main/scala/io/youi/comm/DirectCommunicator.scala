package io.youi.comm

import cats.effect.IO
import profig.Json

class DirectCommunicator[Interface](implementation: CommunicationImplementation[Interface]) extends Communicator {
  override def method(endpoint: String, send: Json): IO[Json] = {
    scribe.info(s"Endpoint: $endpoint, Send: $send")
    implementation.receiveMethod(endpoint, send)
  }
}