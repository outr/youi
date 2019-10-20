package io.youi.app

import io.youi.communication.Connection

import scala.concurrent.duration._

trait ServerConnectedApplication[C <: Connection] extends ServerApplication with YouIConnectedApplication[C] {
  def createConnection(): C
  def connectionTimeout: FiniteDuration = 0.seconds

  val connectionManager: ConnectionManager[C] = new ConnectionManager[C](this)

  override def dispose(): Unit = {
    super.dispose()

    connectionManager.dispose()
  }
}