package io.youi.comm

import cats.effect.IO
import profig.Json

class DirectCommunicator[Interface]() extends Communicator {
  private var implementation: Option[CommunicationImplementation[Interface]] = None

  def register(implementation: CommunicationImplementation[Interface]): Unit = {
    assert(this.implementation.isEmpty, "Only one implementation can be registered!")
    this.implementation = Some(implementation)
  }

  override def method(endpoint: String, send: Json): IO[Json] = {
    scribe.info(s"Endpoint: $endpoint, Send: $send")
    implementation match {
      case Some(i) => i.receiveMethod(endpoint, send)
      case None => IO.raiseError(new RuntimeException("Implementation not defined"))
    }
  }
}