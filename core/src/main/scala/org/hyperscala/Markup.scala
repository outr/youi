package org.hyperscala

import io.HTMLWriter
import org.jdom2.{Attribute, Element, Content}
import annotation.tailrec
import scala.collection.JavaConversions._
import java.util.concurrent.atomic.AtomicBoolean
import org.powerscala.log.Logging
import org.powerscala.event.{Intercept, Listenable}

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Markup extends XMLContent with Listenable with Logging {
  private val _initialized = new AtomicBoolean(false)

  def xmlLabel: String
  def xmlAttributes: Seq[XMLAttribute]
  def xmlChildren: Seq[XMLContent] = Nil
  /**
   * True if tag should never be self-closing even if there are no children.
   *
   * Defaults to false
   */
  def xmlExpanded = false

  /**
   * true if this Markup has been initialized.
   */
  def initialized = _initialized.get()

  /**
   * Iterate over everything
   */
  protected def checkInit(): Unit = {
    if (_initialized.compareAndSet(false, true)) {
      initialize()
    }
    xmlChildren.foreach {
      case markup: Markup => markup.checkInit()
      case _ =>
    }
  }

  def write(writer: HTMLWriter) = {
    checkInit()
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
    Page() match {
      case null => // May not be part of a page
      case page => page.intercept.init.fire(this)
    }
  }

  def onInit(f: => Unit) = Page().intercept.init.on {
    case markup => if (markup == Markup.this) {
      f
    }
  }

  def onBeforeRender(f: => Unit) = Page().intercept.beforeRender.on {
    case markup => {
      if (markup == Markup.this) {
        f
      }
      Intercept.Continue
    }
  }

  def onAfterRender(f: => Unit) = Page().intercept.afterRender.on {
    case markup => if (markup == Markup.this) {
      f
    }
  }

  protected def before(): Unit = {
    Page() match {
      case null => // May not be part of a page
      case page => page.intercept.beforeRender.fire(this)
    }
  }

  protected def after(): Unit = {
    Page() match {
      case null => // May not be part of a page
      case page => page.intercept.afterRender.fire(this)
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
    xmlAttributes.find(xmla => xmla.name == a.getName) match {
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
      warn("%s: Unsupported attribute: %s = %s".format(getClass.getName, name, value))
    }
  }
}

object Markup {
  var UnsupportedAttributeException = true
}