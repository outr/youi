package org.hyperscala

import org.jdom2.{Attribute, Element, Content}
import annotation.tailrec

import scala.collection.JavaConversions._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Markup extends XMLContent {
  protected def xmlLabel: String
  protected def xmlAttributes: Seq[XMLAttribute]

  def toXML: Content = {
    before()
    val element = new Element(xmlLabel)
    attributesToXML(element, xmlAttributes)
    after()
    element
  }
  def fromXML(xml: Content) = xml match {
    case element: Element => {
      attributesFromXML(element.getAttributes)
    }
    case _ => throw new RuntimeException("Unsupported content type: %s".format(xml.getClass.getName))
  }

  protected def before() = {}

  protected def after() = {}

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
      xmlAttributes.find(xmla => xmla.name() == a.getName) match {
        case Some(xmla) => xmla.attributeValue = a.getValue
        case None => unsupportedAttribute(a.getName, a.getValue)
      }
      attributesFromXML(attributes.tail)
    }
  }

  protected def unsupportedAttribute(name: String, value: String) = {
    throw new RuntimeException("Unsupported attribute: %s = %s".format(name, value))
  }
}