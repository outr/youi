package io.youi.server

import cats.effect.IO
import io.youi.http.{ConnectionStatus, HttpConnection, WebSocket}

import scala.concurrent.Future

class WebSocketListener(val httpConnection: HttpConnection) extends WebSocket {
  override def connect(): IO[ConnectionStatus] = {
    _status @= ConnectionStatus.Open
    IO.pure(ConnectionStatus.Open)
  }

  override def disconnect(): Unit = {
    _status @= ConnectionStatus.Closed
    send.close @= ()
  }
}

object WebSocketListener {
  val key: String = "webSocketListener"
}