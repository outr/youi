package io.youi.comm

import cats.effect.IO
import profig.Json

trait CommunicationImplementation[Interface] {
  def receiveMethod(endpoint: String, receive: Json): IO[Json]
}