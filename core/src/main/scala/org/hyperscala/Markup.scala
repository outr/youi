package org.hyperscala

import org.jdom2.{Attribute, Element, Content}
import annotation.tailrec


/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Markup extends XMLContent {
  def xmlLabel: String
  def xmlAttributes: Seq[XMLAttribute]

  def toXML: Content = {
    val element = new Element(xmlLabel)
    before(element)
    attributesToXML(element, xmlAttributes)
    after(element)
    element
  }
  def fromXML(xml: Content) = xml match {
    case element: Element => {
      attributesFromXML(element.getAttributes.toList)
    }
    case _ => throw new RuntimeException("%s: Unsupported content type: %s".format(getClass.getName, xml.getClass.getName))
  }

  protected def before(element: Element) = {}

  protected def after(element: Element) = {}

  @tailrec
  private def attributesToXML(element: Element, attributes: Seq[XMLAttribute]): Unit = {
    if (attributes.nonEmpty) {
      val a = attributes.head
      if (a.shouldRender && a.attributeValue != null) {
        element.setAttribute(a.name(), a.attributeValue)
      }
      attributesToXML(element, attributes.tail)
    }
  }

  @tailrec
  private def attributesFromXML(attributes: Seq[Attribute]): Unit = {
    if (attributes.nonEmpty) {
      val a = attributes.head
      if (!attributeFromXML(a)) {
        unsupportedAttribute(a.getName, a.getValue)
      }
      attributesFromXML(attributes.tail)
    }
  }

  protected def attributeFromXML(a: Attribute): Boolean = {
    xmlAttributes.find(xmla => xmla.name() == a.getName) match {
      case Some(xmla) => {
        xmla.attributeValue = a.getValue
        true
      }
      case None => false
    }
  }

  protected def unsupportedAttribute(name: String, value: String) = {
    if (Markup.UnsupportedAttributeException) {
      throw new RuntimeException("%s: Unsupported attribute: %s = %s".format(getClass.getName, name, value))
    } else {
      System.err.println("%s: Unsupported attribute: %s = %s".format(getClass.getName, name, value))
    }
  }
}

object Markup {
  var UnsupportedAttributeException = true
}