package io.youi.app

import io.youi.http._
import io.youi.server.Server
import io.youi.server.handler.HttpHandler

trait ServerApplication extends YouIApplication with Server {
  handler.matcher(path.exact(connectionPath)).wrap(ServerConnectionHandler)

  protected def page(page: Page): Page = {
    handlers += page
    page
  }

  private object ServerConnectionHandler extends HttpHandler {
    override def handle(httpConnection: HttpConnection): Unit = activeConnections.synchronized {
      val connection = new Connection(ServerApplication.this)
      connection.store.update("httpConnection", httpConnection)
      activeConnections := (activeConnections() + connection)
      connection.connected.distinct.attach { b =>
        if (!b) activeConnections.synchronized {
          activeConnections := (activeConnections() - connection)
        }
      }
      httpConnection.webSocketSupport = connection
    }
  }
}