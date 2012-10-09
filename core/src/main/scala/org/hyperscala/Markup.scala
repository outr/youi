package org.hyperscala

import org.jdom2.{Attribute, Element, Content}
import annotation.tailrec
import scala.collection.JavaConversions._
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Markup extends XMLContent {
  private val initialized = new AtomicBoolean(false)

  def xmlLabel: String
  def xmlAttributes: Seq[XMLAttribute]

  def toXML: Content = {
    if (initialized.compareAndSet(false, true)) {
      initialize()
    }
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

  protected def initialize() = {
    Page().init(this)
  }

  protected def before(element: Element) = {
    Page().before(this)
  }

  protected def after(element: Element) = {
    Page().after(this)
  }

  @tailrec
  private def attributesToXML(element: Element, attributes: Seq[XMLAttribute]): Unit = {
    if (attributes.nonEmpty) {
      attributeToXML(element, attributes.head)
//      if (a.shouldRender && a.attributeValue != null) {
//        element.setAttribute(a.name(), a.attributeValue)
//      }
      attributesToXML(element, attributes.tail)
    }
  }

  protected def attributeToXML(element: Element, attribute: XMLAttribute) = {
    attribute.write(this, element)
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
        xmla.read(this, a.getValue)
//        xmla.attributeValue = a.getValue
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