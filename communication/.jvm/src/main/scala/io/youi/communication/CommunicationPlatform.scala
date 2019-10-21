package io.youi.communication

import java.io.{File, FileOutputStream}

object CommunicationPlatform {
  def createWriter(fileName: String, bytes: Long): ByteBufferWriter = {
    // TODO: configure temp directory
    val file = File.createTempFile("youi-", fileName)
    val channel = new FileOutputStream(file).getChannel
    ChannelWriter(fileName, file.getName, channel, bytes)
  }
}