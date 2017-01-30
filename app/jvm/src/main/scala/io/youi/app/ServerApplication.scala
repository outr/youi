package io.youi.app

import com.outr.reactify.{Val, Var}
import io.youi.http._
import io.youi.server.Server
import io.youi.server.handler.HttpHandler

trait ServerApplication extends YouIApplication with Server {
  private val activeConnections = Var[Set[Connection]](Set.empty)
  override def connections: Val[Set[Connection]] = Val(activeConnections)

  handler.matcher(path.exact(connectionPath)).wrap(ServerConnectionHandler)

  private object ServerConnectionHandler extends HttpHandler {
    override def handle(httpConnection: HttpConnection): Unit = activeConnections.synchronized {
      val connection = new Connection
      activeConnections := activeConnections + connection
      connection.connected.distinct.attach { b =>
        if (!b) activeConnections.synchronized {
          activeConnections := activeConnections() - connection
        }
      }
      httpConnection.webSocketSupport = connection
    }
  }
}