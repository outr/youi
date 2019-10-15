package io.youi.server

import io.youi.communication.Connection

trait ConnectionManager[C <: Connection] {
  protected def create(): C
}