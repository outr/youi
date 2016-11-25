package io.youi.communicate

import io.youi.http.{HttpConnection, WebSocketListener}
import io.youi.server.handler.HttpHandler

trait ServerWebSocketCommunication extends Communication with HttpHandler {
  private var connections = Set.empty[WebSocketConnection]
  private val _connection = new ThreadLocal[Option[WebSocketConnection]] {
    override def initialValue(): Option[WebSocketConnection] = None
  }
  def connectionOption: Option[WebSocketConnection] = _connection.get()
  def connection: WebSocketConnection = connectionOption.getOrElse(throw new RuntimeException(s"No WebSocketConnection on current thread."))

  override def send(messageId: Int, invocationId: Int, invocationType: InvocationType, message: Option[String]): Unit = {
    connection.send.text := s"$messageId:$invocationId:${invocationType.id}:${message.getOrElse("")}"
  }

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
    case WebSocketConnection.MessageRegex(messageId, invocationId, invocationType, message) => communication.withConnection(this) {
      communication.receive(messageId.toInt, invocationId.toInt, InvocationType(invocationType.toInt), if (message.nonEmpty) Some(message) else None)
    }
  }
}

object WebSocketConnection {
  private val MessageRegex = """(\d+):(\d+):(\d+):(.*)""".r
}