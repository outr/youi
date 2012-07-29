package org.hyperscala

import org.powerscala.hierarchy.MutableContainer
import annotation.tailrec
import org.jdom2.{Content, Element}

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
    // TODO: determine how to load children and determine proper classes to instantiate
  }

  @tailrec
  private def childrenToXML(element: Element, children: Seq[C]): Unit = {
    if (children.nonEmpty) {
      val child = children.head
      element.addContent(child.toXML)
      childrenToXML(element, children.tail)
    }
  }
}
