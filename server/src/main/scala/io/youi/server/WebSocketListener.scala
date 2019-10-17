package io.youi.server

import io.youi.http.{ConnectionStatus, HttpConnection, WebSocket}

import scala.concurrent.Future

class WebSocketListener(val httpConnection: HttpConnection) extends WebSocket {
  override def connect(): Future[ConnectionStatus] = {
    _status @= ConnectionStatus.Open
    Future.successful(ConnectionStatus.Open)
  }

  override def disconnect(): Unit = {
    _status @= ConnectionStatus.Closed
    send.close @= ()
  }
}

object WebSocketListener {
  val key: String = "webSocketListener"
}