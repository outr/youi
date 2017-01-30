package io.youi.stream

import java.nio.ByteBuffer
import java.nio.channels.SeekableByteChannel

import scala.annotation.tailrec

class ByteReader(startingSize: Int = 4096) {
  private var bytes = new Array[Byte](startingSize)

  def byteBuffer(length: Int): ByteBuffer = {
    if (length > bytes.length) {
      bytes = new Array[Byte](Math.max(length, bytes.length * 2))
    }
    ByteBuffer.wrap(bytes, 0, length)
  }

  def readString(position: Int, length: Int, channel: SeekableByteChannel): String = {
    val bb = byteBuffer(length)
    channel.position(position)
    readRecursive(channel, bb)
    new String(bytes, 0, length, "UTF-8")
  }

  @tailrec
  private def readRecursive(channel: SeekableByteChannel, bb: ByteBuffer): Unit = {
    val read = channel.read(bb)
    if (!bb.hasRemaining) {
      // Finished
    } else if (read == -1) {
      throw new RuntimeException(s"Unable to complete, ran out of file to read!")
    } else {
      readRecursive(channel, bb)
    }
  }
}