package org.hyperscala

import org.powerscala.hierarchy.MutableContainer
import annotation.tailrec
import org.jdom2.{Text, Content, Element}
import scala.collection.JavaConversions._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Container[C <: XMLContent] extends MutableContainer[C] with Markup {
  override def toXML = {
    val element = super.toXML.asInstanceOf[Element]
    childrenToXML(element, contents)
    element
  }

  override def fromXML(xml: Content) {
    super.fromXML(xml)
    val element = xml.asInstanceOf[Element]
    element.getContent.foreach {
      case childElement: Element => {
        val child = generateChildFromTagName(childElement.getName).asInstanceOf[C]
        child.fromXML(childElement)
        contents += child
      }
      case childText: Text => processText(childText.getText)
    }
  }

  protected def generateChildFromTagName(name: String): XMLContent

  protected def processText(text: String): Unit

  @tailrec
  private def childrenToXML(element: Element, children: Seq[C]): Unit = {
    if (children.nonEmpty) {
      val child = children.head
      element.addContent(child.toXML)
      childrenToXML(element, children.tail)
    }
  }
}
