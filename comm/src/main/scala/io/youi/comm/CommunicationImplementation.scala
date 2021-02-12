package io.youi.comm

import cats.effect.IO
import profig.Json

trait CommunicationImplementation[Interface] extends Communication {
  def implementation: Interface

  def receiveMethod(endpoint: String, receive: Json): IO[Json]
}