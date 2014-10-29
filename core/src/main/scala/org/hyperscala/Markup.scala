package org.hyperscala

import io.HTMLWriter
import org.jdom2.{Attribute, Element, Content}
import annotation.tailrec
import scala.collection.JavaConversions._
import java.util.concurrent.atomic.AtomicBoolean
import org.powerscala.log.Logging
import org.powerscala.event.{Listener, Intercept, Listenable}
import org.powerscala.event.processor.InterceptProcessor
import org.powerscala.hierarchy.event.{Ancestors, ChildAddedEvent}
import org.powerscala.Priority

/**
 * @author Matt Hicks <matt@outr.com>
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
  protected final def writeChildren(writer: HTMLWriter, children: Seq[XMLContent]): Unit = {
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

  private def page = root[Page].getOrElse(throw new RuntimeException("No Page instance defined in hierarchy."))

  protected def initialize(): Unit = root[Page] match {
    case Some(page) => page.intercept.init.fire(this)
    case None => // Not connected to a page
  }

  /**
   * Invokes the supplied function upon init or immediately if this Markup has already been initialized.
   *
   * @param f represents the function to be called upon init
   */
  def onInit(f: => Unit): Unit = connected[Page] {
    case page => if (!initialized) {
      page.intercept.init.on {
        case markup => if (markup == Markup.this) {
          f
        }
      }
    } else {
      f
    }
  }

  /**
   * Invokes the function before rendering of this markup. If the markup is already rendered then the function is
   * invoked immediately.
   */
  def onBeforeRender(f: => Unit) = connected[Page] {
    case page => if (rendered) {
      f
    } else {
      page.intercept.beforeRender.on {
        case markup => {
          if (markup == Markup.this) {
            f
          }
          Intercept.Continue
        }
      }
    }
  }

  /**
   * Invokes the function before rendering of this markup. If the markup is already rendered then the function is
   * invoked immediately.
   */
  def onAfterRender(f: => Unit) = connected[Page] {
    case page => if (rendered) {
      f
    } else {
      page.intercept.afterRender.on {
        case markup => {
          if (markup == Markup.this) {
            f
          }
        }
      }
    }
  }

  /**
   * Invoked immediately before writing this markup out.
   */
  protected def before(): Unit = root[Page] match {
    case Some(page) => page.intercept.beforeRender.fire(this)
    case None => // Not connected to a page
  }

  /**
   * Invoked immediately after writing this markup out.
   */
  protected def after(): Unit = root[Page] match {
    case Some(page) => page.intercept.afterRender.fire(this)
    case None => // Not connected to a page
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
    if (!attributeFromXML(a) && Markup.attributeSet.fire(this -> a) == Intercept.Continue) {
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

object Markup extends Listenable {
  var UnsupportedAttributeException = true

  /**
   * attribute set is called when an attribute is unable to be derived for setting the value. If Intercept.Stop is
   * returned then the system will assume the attribute was successfully applied.
   */
  val attributeSet = new InterceptProcessor[(Markup, Attribute)]("attributeSet")

  def rendered(markup: Markup) = markup._rendered.set(true)
}