package io.youi.http

import reactify.Channel

class WebSocketChannels {
  val text: Channel[String] = Channel[String]
  val binary: Channel[BinaryData] = Channel[BinaryData]
  val close: Channel[Unit] = Channel[Unit]
}