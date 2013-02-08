package org.hyperscala.html

import constraints.BodyChild
import org.hyperscala.io.HTMLWriter
import java.net.URL
import org.powerscala.IO

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class StaticHTML(val content: String) extends HTMLTag with BodyChild {
  def this(url: URL) = this(IO.copy(url))

  def xmlLabel: String = null

  override protected def writeTag(writer: HTMLWriter) = {
    writer.write(content)
  }
}