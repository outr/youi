package io.youi.http

import reactify.{Channel, Val, Var}

trait WebSocket {
  protected val _connected: Var[Boolean] = Var[Boolean](false)
  val connected: Val[Boolean] = Val(_connected)

  val send: WebSocketChannels = new WebSocketChannels
  val receive: WebSocketChannels = new WebSocketChannels
  val error: Channel[Throwable] = Channel[Throwable]
}
