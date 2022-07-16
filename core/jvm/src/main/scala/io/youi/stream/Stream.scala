package io.youi.stream

import java.io.{File, FileOutputStream, IOException}
import scala.annotation.tailrec
import scala.concurrent.duration.{DurationInt, FiniteDuration}

object Stream {
  final def apply(reader: Reader,
                  writer: Writer,
                  monitor: Monitor = Monitor.Ignore,
                  monitorDelay: FiniteDuration = 15.millis,
                  buffer: Array[Byte] = new Array[Byte](1024),
                  closeOnComplete: Boolean = true): Writer = {
    val length = reader.length
    monitor.open(length)
    var total = 0L

    val delay = monitorDelay.toMillis
    var lastNotified = 0L

    @tailrec
    def recurse(): Unit = {
      val len = reader.read(buffer)
      if (len == -1) {
        writer.flush()
        if (closeOnComplete) {
          writer.close()
          reader.close()
          monitor.closed()
        }
        writer.complete()
        monitor.completed()
      } else {
        try {
          writer.write(buffer, 0, len)
          total += len
          val percentage = length.map { l =>
            (total.toDouble / l.toDouble) * 100.0
          }
          val now = System.currentTimeMillis()
          val elapsed = now - lastNotified
          if (elapsed >= delay) {
            monitor.written(len, total, percentage)
            lastNotified = now
          }
        } catch {
          case t: Throwable => {
            monitor.failure(t)
            throw new IOException(s"IO failed to write to writer with length: $len with reader: $reader, writer: $writer.", t)
          }
        }
        recurse()
      }
    }

    recurse()
    writer
  }

  /**
    * Uses IO.stream, but supports recursive directory copying.
    *
    * @param source file or directory
    * @param destination file or directory
    */
  def copy(source: File, destination: File): Unit = if (source.isDirectory) {
    destination.mkdirs()
    assert(destination.isDirectory, s"Destination ${destination.getAbsolutePath} is a file, not a directory!")
    source.listFiles().foreach { file =>
      copy(file, new File(destination, file.getName))
    }
  } else if (source.isFile) {
    if (destination.isDirectory) {
      apply(source, new File(destination, source.getName))
    } else {
      apply(source, destination)
    }
  }

  def merge(sources: List[File], destination: File): Unit = {
    val outputStream = new FileOutputStream(destination)
    sources.foreach { source =>
      apply(source, outputStream, closeOnComplete = false)
    }
    outputStream.flush()
    outputStream.close()
  }

  def delete(file: File): Boolean = {
    if (file.isDirectory) {
      deleteFiles(file.listFiles().toList)
    }
    file.delete()
  }

  @tailrec
  final def deleteFiles(files: List[File]): Unit = {
    if (files.nonEmpty) {
      val f = files.head
      delete(f)
      deleteFiles(files.tail)
    }
  }
}