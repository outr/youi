package io.youi.app

import io.youi.http._
import io.youi.server.Server
import io.youi.server.handler.HttpHandler

trait ServerApplication extends YouIApplication with Server {
  private lazy val _connection = new ThreadLocal[Option[Connection]] {
    override def initialValue(): Option[Connection] = None
  }

  handler.matcher(path.exact(connectionPath)).wrap(ServerConnectionHandler)

  def connectionOption: Option[Connection] = _connection.get()
  override def connection: Connection = connectionOption.getOrElse(throw new RuntimeException("No connection defined on the current thread."))
  override def withConnection[R](connection: Connection)(f: => R): R = {
    val previous = _connection.get()
    _connection.set(Some(connection))
    try {
      f
    } finally {
      _connection.set(previous)
    }
  }

  private object ServerConnectionHandler extends HttpHandler {
    override def handle(httpConnection: HttpConnection): Unit = activeConnections.synchronized {
      val connection = new Connection(ServerApplication.this)
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