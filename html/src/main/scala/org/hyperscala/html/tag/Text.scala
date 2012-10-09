package org.hyperscala.html.tag

import org.jdom2.Content
import org.hyperscala.{TextMarkup, Textual}
import org.jdom2.{Text => JDOMText}
import org.hyperscala.html.constraints.{HTMLChild, HeadChild, BodyChild}
import org.hyperscala.html.HTMLTag
import org.hyperscala.io.HTMLWriter

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Text extends Textual with HTMLChild with BodyChild with HeadChild with HTMLTag with TextMarkup {
  def this(content: String) = {
    this()
    this.content := content
  }

  override def render = content() != null && content().nonEmpty && super.render

  def xmlLabel = null

  override protected def writeTag(writer: HTMLWriter) {
    writer.write(content())
  }

  override def read(xml: Content) = content := xml.asInstanceOf[JDOMText].getText
}
