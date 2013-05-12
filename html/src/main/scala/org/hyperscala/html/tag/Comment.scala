package org.hyperscala.html.tag

import org.hyperscala.io.HTMLWriter
import org.jdom2.{Comment => JDOMComment, Content}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Comment extends Text {
  def this(content: String) = {
    this()
    this.content := content
  }

  override protected def writeTag(writer: HTMLWriter) = if (content() != null) {
    writer.write(s"<!--${content()}-->")
  }

  override def read(xml: Content) = content := xml.asInstanceOf[JDOMComment].getText
}
