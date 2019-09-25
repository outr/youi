package io.youi.http

import java.nio.ByteBuffer

import reactify.Channel

class WebSocketChannels {
  val text: Channel[String] = Channel[String]
  val binary: Channel[ByteBuffer] = Channel[ByteBuffer]
  val close: Channel[Unit] = Channel[Unit]
}