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
  private val _rendered = new AtomicBoolean(false)

  def xmlLabel: String
  def xmlAttributes: Iterable[XMLAttribute]
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
   * true if this Markup has been rendered.
   */
  def rendered = _rendered.get()

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
    writeExtra(writer)
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
  private def writeAttributes(writer: HTMLWriter, attributes: Iterable[XMLAttribute]): Unit = {
    if (attributes.nonEmpty) {
      writeAttribute(writer, attributes.head)
      writeAttributes(writer, attributes.tail)
    }
  }

  protected def writeAttribute(writer: HTMLWriter, attribute: XMLAttribute): Unit = {
    attribute.write(this, writer)
  }

  protected def writeExtra(writer: HTMLWriter): Unit = {}

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

  /**
   * Invokes the supplied function upon init or immediately if this Markup has already been initialized.
   *
   * @param f represents the function to be called upon init
   */
  def onInit(f: => Unit): Unit = if (!initialized) {
    Page().intercept.init.on {
      case markup => if (markup == Markup.this) {
        f
      }
    }
  } else {
    f
  }

  /**
   * Invokes the function before rendering of this markup. If the markup is already rendered then the function is
   * invoked immediately.
   */
  def onBeforeRender(f: => Unit) = if (rendered) {
    f
  } else {
    Page().intercept.beforeRender.on {
      case markup => {
        if (markup == Markup.this) {
          f
        }
        Intercept.Continue
      }
    }
  }

  /**
   * Invokes the function before rendering of this markup. If the markup is already rendered then the function is
   * invoked immediately.
   */
  def onAfterRender(f: => Unit) = if (rendered) {
    f
  } else {
    Page().intercept.afterRender.on {
      case markup => if (markup == Markup.this) {
        f
      }
    }
  }

  /**
   * Invoked immediately before writing this markup out.
   */
  protected def before(): Unit = {
    Page() match {
      case null => // May not be part of a page
      case page => page.intercept.beforeRender.fire(this)
    }
  }

  /**
   * Invoked immediately after writing this markup out.
   */
  protected def after(): Unit = Page() match {
    case null => // May not be part of a page
    case page => page.intercept.afterRender.fire(this)
  }

  @tailrec
  private def attributesFromXML(attributes: Seq[Attribute]): Unit = {
    if (attributes.nonEmpty) {
      val a = attributes.head
      applyAttribute(a)
      attributesFromXML(attributes.tail)
    }
  }

  protected def applyAttribute(a: Attribute) = {
    if (!attributeFromXML(a)) {
      unsupportedAttribute(a.getName, a.getValue)
    }
  }

  protected def attributeFromXML(a: Attribute): Boolean

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

  def rendered(markup: Markup) = markup._rendered.set(true)
}