package io.youi.http

import reactify.{Channel, Val, Var}

import scala.concurrent.Future

trait WebSocket {
  protected val _status: Var[ConnectionStatus] = Var(ConnectionStatus.Closed)
  val status: Val[ConnectionStatus] = _status

  val send: WebSocketChannels = new WebSocketChannels
  val receive: WebSocketChannels = new WebSocketChannels
  val error: Channel[Throwable] = Channel[Throwable]

  def connect(): Future[Unit]

  def disconnect(): Unit
}