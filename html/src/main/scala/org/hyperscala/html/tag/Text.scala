package org.hyperscala.html.tag

import org.jdom2.Content
import org.hyperscala.Textual
import org.jdom2.{Text => JDOMText}
import org.hyperscala.html.constraints.{HeadChild, BodyChild}
import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Text extends Textual with BodyChild with HeadChild with HTMLTag {
  def this(content: String) = {
    this()
    this.content := content
  }

  override def render = content() != null && content().trim.nonEmpty && super.render

  def xmlLabel = null

  override def toXML = new JDOMText(content())

  override def fromXML(xml: Content) = content := xml.asInstanceOf[JDOMText].getTextTrim
}
