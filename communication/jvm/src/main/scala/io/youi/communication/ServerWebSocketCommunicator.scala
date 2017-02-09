package io.youi.communication

import io.youi.http.{Connection, HttpConnection}
import io.youi.server.handler.HttpHandler

trait ServerWebSocketCommunicator[C <: Communication] extends HttpHandler {
  protected def create(connection: Connection): C

  override def handle(httpConnection: HttpConnection): Unit = synchronized {
    val connection = new Connection
    connection.store.update("httpConnection", httpConnection)
    create(connection)
    httpConnection.webSocketSupport = connection
  }
}
