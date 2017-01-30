package io.youi.communication

import io.youi.http.{Connection, HttpConnection}
import io.youi.server.handler.HttpHandler

trait ServerWebSocketCommunicator[C <: Communication] extends HttpHandler {
  protected def create(connection: Connection): C

  override def handle(httpConnection: HttpConnection): Unit = synchronized {
    val connection = new Connection
    val communication = create(connection)
    connection.receive.text.attach {
      case CommunicationMessage(message) => communication.comm.receive := message
    }
    communication.comm.send.attach { message =>
      connection.send.text := message.parsableString
    }
    httpConnection.webSocketSupport = connection
  }
}
