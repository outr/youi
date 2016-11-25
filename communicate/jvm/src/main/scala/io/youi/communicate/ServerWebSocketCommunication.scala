package io.youi.communicate

import io.youi.http.{HttpConnection, WebSocketListener}
import io.youi.server.handler.HttpHandler

trait ServerWebSocketCommunication extends WebSocketCommunication with HttpHandler {
  private var connections = Set.empty[WebSocketConnection]
  private val _connection = new ThreadLocal[Option[WebSocketConnection]] {
    override def initialValue(): Option[WebSocketConnection] = None
  }
  def connectionOption: Option[WebSocketConnection] = _connection.get()
  def connection: WebSocketConnection = connectionOption.getOrElse(throw new RuntimeException(s"No WebSocketConnection on current thread."))

  override protected def webSocketListener: WebSocketListener = connection

  override def handle(connection: HttpConnection): Unit = synchronized {
    val c = new WebSocketConnection(this)
    connections += c
    connection.webSocketSupport = c
  }

  protected[communicate] def withConnection[R](connection: WebSocketConnection)(f: => R): R = {
    val previous = _connection.get()
    try {
      f
    } finally {
      _connection.set(previous)
    }
  }
}

class WebSocketConnection(communication: ServerWebSocketCommunication) extends WebSocketListener {
  receive.text.attach {
    case CommunicationMessage(message) => communication.withConnection(this) {
      communication.receive(message)
    }
  }
}