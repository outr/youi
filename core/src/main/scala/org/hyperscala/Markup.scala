package org.hyperscala

import io.HTMLWriter
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
  def xmlChildren: Seq[XMLContent] = Nil
  /**
   * True if tag should never be self-closing even if there are no children.
   *
   * Defaults to false
   */
  def xmlExpanded = false

  def write(writer: HTMLWriter) = {
    if (initialized.compareAndSet(false, true)) {
      initialize()
    }
    before()
    writeTag(writer)
    after()
  }

  protected def writeTag(writer: HTMLWriter) = {
    writer.write(writer.newLine)
    writer.writeTabs()
    writer.write("<")
    writer.write(xmlLabel)
    writeAttributes(writer, xmlAttributes)
    val children = xmlChildren
    if (xmlExpanded || children.nonEmpty) {
      writer.write(">")
      writer.tabbed {
        writeChildren(writer, children)
      }
      if (children.nonEmpty && children.find(c => !c.isInstanceOf[TextMarkup]).nonEmpty && !this.isInstanceOf[Textual]) {
        writer.write(writer.newLine)
        writer.writeTabs()
      }
      writer.write("</%s>".format(xmlLabel))
    } else {
      writer.write("/>")
    }
  }

  @tailrec
  private def writeAttributes(writer: HTMLWriter, attributes: Seq[XMLAttribute]): Unit = {
    if (attributes.nonEmpty) {
      writeAttribute(writer, attributes.head)
      writeAttributes(writer, attributes.tail)
    }
  }

  protected def writeAttribute(writer: HTMLWriter, attribute: XMLAttribute): Unit = {
    attribute.write(this, writer)
  }

  @tailrec
  private def writeChildren(writer: HTMLWriter, children: Seq[XMLContent]): Unit = {
    if (children.nonEmpty) {
      writeChild(writer, children.head)
      writeChildren(writer, children.tail)
    }
  }

  protected def writeChild(writer: HTMLWriter, child: XMLContent): Unit = {
    child.write(writer)
  }

  def read(content: Content) = content match {
    case element: Element => {
      attributesFromXML(element.getAttributes.toList)
    }
    case _ => throw new RuntimeException("%s: Unsupported content type: %s".format(getClass.getName, content.getClass.getName))
  }

  protected def initialize(): Unit = {
    Page().intercept.init.fire(this)
  }

  protected def before(): Unit = {
    Page().intercept.beforeRender.fire(this)
  }

  protected def after(): Unit = {
    Page().intercept.afterRender.fire(this)
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