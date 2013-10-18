package org.hyperscala.web

import com.outr.net.http.content.StreamingContent
import java.io.OutputStream
import org.hyperscala.io.HTMLWriter
import java.nio.charset.Charset
import org.hyperscala.html.tag.HTML

/**
 * @author Matt Hicks <matt@outr.com>
 */
class HTMLStreamer(html: HTML) extends StreamingContent {
  val contentType = "text/html; charset=UTF-8"
  val contentLength = -1L
  val lastModified = -1L

  def stream(output: OutputStream) = {
    val writer = HTMLWriter {
      case s => output.write(s.getBytes(Charset.forName("UTF-8")))
    }
    html.write(writer)
  }
}
