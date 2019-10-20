package io.youi.communication

import java.io.{File, FileOutputStream}
import java.nio.ByteBuffer
import java.nio.channels.FileChannel

class ConnectionPlatform(connection: Connection) {
  private var fileReceive: Option[FileReceive] = None

  def receiveBinary(bb: ByteBuffer): Unit = {
    fileReceive match {
      case Some(f) => {
        if (f.fileName == null) { // Read filename
          val array = new Array[Byte](f.bytes.toInt)
          bb.get(array)
          val fileName = new String(array, "UTF-8")
          scribe.info(s"FileName: $fileName")
          fileReceive = Some(f.copy(fileName = fileName, bytes = -1L))
        } else if (f.bytes == -1L) {
          val bytes = bb.getLong
          scribe.info(s"FileLength: $bytes")
          val file = File.createTempFile("youi-", f.fileName)
          val channel = new FileOutputStream(file).getChannel
          fileReceive = Some(f.copy(bytes = bytes, file = file, channel = channel))
        } else {
          val written = f.channel.write(bb)
          val remaining = f.bytes - written
          fileReceive = Some(f.copy(bytes = remaining))
          // TODO: Notify Connection of written bytes and remaining
          scribe.info(s"written: $written, Remaining: $remaining")
          if (remaining == 0L) {
            scribe.info(s"Finished writing ${f.fileName} to ${f.file.getAbsolutePath}")
            f.channel.close()
            // TODO: Send message back to Connection
            fileReceive = None
          }
        }
      }
      case None => {
        val fileNameLength = bb.getInt
        scribe.info(s"FNL: $fileNameLength")
        fileReceive = Some(FileReceive(null, fileNameLength, null, null))
      }
    }
  }

  case class FileReceive(fileName: String, bytes: Long, file: File, channel: FileChannel)
}