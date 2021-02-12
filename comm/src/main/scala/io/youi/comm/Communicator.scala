package io.youi.comm

import cats.effect.IO
import profig.Json

trait Communicator {
  def method(endpoint: String, send: Json): IO[Json]
}