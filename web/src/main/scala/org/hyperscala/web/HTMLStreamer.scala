package org.hyperscala.web

import com.outr.net.http.content.{ContentType, StreamingContent}
import java.io.OutputStream
import org.hyperscala.io.HTMLWriter
import java.nio.charset.Charset
import org.hyperscala.html.tag.HTML
import java.io.ByteArrayOutputStream

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HTMLStreamer(html: HTML) extends StreamingContent {
  val contentType = ContentType.HTML.copy(charSet = "UTF-8")
  val contentLength = -1L
  val lastModified = -1L

  def stream(output: OutputStream) = {
    val writer = HTMLWriter {
      case s => output.write(s.getBytes(contentType.charSet))
    }
    writer.writeLine("<!DOCTYPE html>\r\n")
    html.write(writer)
  }

  /**
   * Convenience method to get the stream as a string.
   */
  override def asString = {
    val baos = new ByteArrayOutputStream
    stream(baos)
    new String(baos.toByteArray, contentType.charSet).replaceAll("""\s""", "")
  }
}
