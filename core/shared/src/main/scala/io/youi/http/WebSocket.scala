package io.youi.http

import reactify.{Channel, Val, Var}

trait WebSocket {
  protected val _status: Var[ConnectionStatus] = Var(ConnectionStatus.Closed)
  val status: Val[ConnectionStatus] = _status

  val send: WebSocketChannels = new WebSocketChannels
  val receive: WebSocketChannels = new WebSocketChannels
  val error: Channel[Throwable] = Channel[Throwable]

  def disconnect(): Unit

  def dispose(): Unit
}