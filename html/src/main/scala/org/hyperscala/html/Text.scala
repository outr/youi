package org.hyperscala.html

import constraints.BodyChild
import org.jdom2.Content
import org.hyperscala.Textual
import org.jdom2.{Text => JDOMText}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class Text extends Textual with BodyChild with HTMLTag {
  def this(content: String) = {
    this()
    this.content := content
  }

  protected def xmlLabel = null

  override def toXML = new JDOMText(content())

  override def fromXML(xml: Content) = content := xml.asInstanceOf[JDOMText].getTextTrim
}
