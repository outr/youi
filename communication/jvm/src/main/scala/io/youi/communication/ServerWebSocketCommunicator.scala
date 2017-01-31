package io.youi.communication

import io.youi.http.{Connection, ConnectionManager, HttpConnection}
import io.youi.server.handler.HttpHandler

trait ServerWebSocketCommunicator[C <: Communication] extends HttpHandler with ConnectionManager {
  protected def create(connection: Connection): C

  override def handle(httpConnection: HttpConnection): Unit = synchronized {
    val connection = new Connection(this)
    val communication = create(connection)
    httpConnection.webSocketSupport = connection
  }
}
