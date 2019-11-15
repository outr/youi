package io.youi.communication

import java.nio.ByteBuffer
import java.nio.channels.FileChannel

case class ChannelWriter(fileName: String, actualFileName: String, channel: FileChannel, bytes: Long) extends ByteBufferWriter {
  private var _written: Long = 0L

  override def written: Long = _written

  override def remaining: Long = bytes - written

  override def write(bb: ByteBuffer): Unit = {
    _written += channel.write(bb)
  }

  override def close(): Unit = channel.close()
}