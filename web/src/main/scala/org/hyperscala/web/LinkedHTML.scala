package org.hyperscala.web

import org.hyperscala.html.HTMLTag
import io.Source
import java.net.URL
import org.hyperscala.Container
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.io.HTMLWriter
import site.realtime.Realtime
import site.Webpage

/**
 * LinkedHTML works similarly to StaticHTML by providing pre-defined HTML into a Hyperscala page, but also allows
 * linking to components within the static HTML for modification via Realtime.
 *
 * @author Matt Hicks <mhicks@outr.com>
 */
abstract class LinkedHTML(val content: String) extends Container[HTMLTag] with BodyChild with HTMLTag {
  Webpage().require(Realtime)

  private var modified = false

  def this(source: Source) = this(source.mkString)
  def this(url: URL) = this(Source.fromURL(url))

  def xmlLabel: String = "linkedhtml"

  override protected def writeTag(writer: HTMLWriter) = {
    writer.write(content)
  }

  def link[T <: HTMLTag](tag: T) = {
    contents += tag
    tag
  }

  /**
   * Modifications to tags added to this LinkedHTML are not safe to be modified until this method is invoked. All
   * modifications to the tags before this method call will not be synchronized to the client. This method is invoked
   * immediately after the first page render.
   */
  def modify(): Unit

  override def rendered() {
    super.rendered()

    if (!modified) {
      modify()
      modified = true
    }
  }
}