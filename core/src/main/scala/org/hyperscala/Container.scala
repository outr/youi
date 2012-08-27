package org.hyperscala

import org.powerscala.hierarchy.MutableContainer
import annotation.tailrec
import org.jdom2.{Comment, Text, Content, Element}
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
        if (!contents.contains(child)) {
          contents += child
        }
      }
      case childText: Text => processText(childText.getText)
      case childComment: Comment => // Ignore comments
    }
  }

  protected def generateChildFromTagName(name: String): XMLContent

  protected def processText(text: String): Unit

  @tailrec
  private def childrenToXML(element: Element, children: Seq[C]): Unit = {
    if (children.nonEmpty) {
      val child = children.head
      if (child.render) {
        element.addContent(child.toXML)
      }
      childrenToXML(element, children.tail)
    }
  }
}
