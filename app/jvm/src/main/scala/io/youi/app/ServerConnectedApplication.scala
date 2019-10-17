package io.youi.app

import io.youi.communication.Connection

trait ServerConnectedApplication[C <: Connection] extends ServerApplication with YouIConnectedApplication[C] {
  def createConnection(): C

  val connectivity: ConnectionManager[C] = new ConnectionManager[C](this)
}
