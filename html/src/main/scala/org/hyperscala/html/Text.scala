package org.hyperscala.html

import constraints.{HeadChild, BodyChild}
import org.jdom2.Content
import org.hyperscala.Textual
import org.jdom2.{Text => JDOMText}

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
