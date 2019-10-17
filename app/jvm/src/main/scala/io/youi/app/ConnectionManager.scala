package io.youi.app

import io.youi.communication.Connection
import io.youi.server.WebSocketListener
import reactify.{Val, Var}

import scala.concurrent.Future

class ConnectionManager[C <: Connection](app: ServerConnectedApplication[C]) {
  private val _connections: Var[List[Connectivity[C]]] = Var(Nil)

  val connections: Val[List[Connectivity[C]]] = _connections

  app.handler.handle { connection =>
    Future.successful {
      if (connection.request.url.path == app.communicationPath) {
        val (updated, listener) = connection.withWebSocketListener()
        addConnection(listener)
        updated
      } else {
        connection
      }
    }
  }

  private def addConnection(listener: WebSocketListener): Unit = synchronized {
    val connection = app.createConnection()
    val connectivity = new Connectivity[C](connection)
    connectivity.webSocket := Some(listener)
    _connections @= connectivity :: _connections()
  }

  // TODO: Every thirty seconds unless killed by app.dispose
  // TODO: Ping / Pong - Shouldn't it be the client, not the server?
  // TODO: Timeout long-closed connections

  private def removeConnection(connectivity: Connectivity[C]): Unit = synchronized {
    _connections @= _connections.filterNot(_ eq connectivity)
    connectivity.disconnect()
    connectivity.connection.queue.dispose()
  }
}