package org.hyperscala.html

import constraints.BodyChild
import org.hyperscala.io.HTMLWriter
import io.Source
import java.net.URL

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class StaticHTML(val content: String) extends HTMLTag with BodyChild {
  def this(source: Source) = this(source.mkString)
  def this(url: URL) = this(Source.fromURL(url))

  def xmlLabel: String = null

  override protected def writeTag(writer: HTMLWriter) = {
    writer.write(content)
  }
}