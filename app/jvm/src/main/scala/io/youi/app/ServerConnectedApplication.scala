package io.youi.app

import io.youi.communication.Connection
import io.youi.server.WebSocketListener

import scala.concurrent.duration._

trait ServerConnectedApplication[C <: Connection] extends ServerApplication with YouIConnectedApplication[C] {
  def getOrCreateConnection(listener: WebSocketListener): C
  def connectionTimeout: FiniteDuration = 90.seconds

  val connectionManager: ConnectionManager[C] = new ConnectionManager[C](this)

  override def dispose(): Unit = {
    super.dispose()

    connectionManager.dispose()
  }
}