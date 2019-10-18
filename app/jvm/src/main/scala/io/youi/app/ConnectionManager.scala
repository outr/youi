package io.youi.app

import io.youi.communication.Connection
import io.youi.http.ConnectionStatus
import io.youi.server.WebSocketListener
import io.youi.util.Time
import reactify.{Val, Var}

import scala.concurrent.Future
import scala.concurrent.duration._
import scribe.Execution.global

class ConnectionManager[C <: Connection](app: ServerConnectedApplication[C]) {
  private val _connections: Var[List[C]] = Var(Nil)

  val connections: Val[List[C]] = _connections

  private val connectionMonitor = Time.repeat(
    delay = 30.seconds,
    stopOnError = false,
    errorHandler = app.error
  )(update())

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
    connection.webSocket := Some(listener)
    _connections @= connection :: _connections()
  }

  // TODO: Every thirty seconds unless killed by app.dispose
  // TODO: Ping / Pong - Shouldn't it be the client, not the server?
  // TODO: Timeout long-closed connections

  private def removeConnection(connection: C): Unit = synchronized {
    _connections @= _connections.filterNot(_ eq connection)
    connection.disconnect()
    connection.queue.dispose()
  }

  private def update(): Future[Unit] = {
    connections.foreach { connection =>
      if (connection.status() == ConnectionStatus.Closed) {
        val closedFor = System.currentTimeMillis() - connection.lastActive()
        if (closedFor > app.connectionTimeout.toMillis) {
          scribe.info(s"Connection timeout, disposing!")
          removeConnection(connection)
        }
      }
    }

    Future.successful(())
  }

  def dispose(): Unit = {
    connectionMonitor.stop()
  }
}