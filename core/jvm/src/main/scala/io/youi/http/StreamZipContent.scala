package io.youi.http

import java.io.OutputStream
import java.util.zip.{ZipEntry, ZipOutputStream}

import io.youi.net.ContentType
import org.powerscala.io._

class StreamZipContent(entries: List[ZipFileEntry],
                       lastModified: Long = System.currentTimeMillis(),
                       length: Long = -1) extends StreamContent(ContentType.`application/zip`, lastModified, length) {
  override def stream(out: OutputStream): Unit = {
    val zos = new ZipOutputStream(out)
    entries.foreach { e =>
      val entry = new ZipEntry(e.path)
      zos.putNextEntry(entry)
      IO.stream(e.file, zos, closeOnComplete = false)
      zos.closeEntry()
    }
    zos.flush()
    zos.close()
  }
}
