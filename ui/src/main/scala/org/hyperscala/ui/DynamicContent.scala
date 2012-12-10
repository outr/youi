package org.hyperscala.ui

import org.hyperscala.html.HTMLTag
import org.jdom2.Element
import org.hyperscala.Container
import org.hyperscala.html.constraints.BodyChild
import org.hyperscala.io.HTMLWriter
import org.jdom2.input.SAXBuilder
import java.io.StringReader
import annotation.tailrec
import org.powerscala.property.{CaseClassBinding, StandardProperty}
import org.hyperscala.web.site.Webpage
import org.hyperscala.web.site.realtime.Realtime

/**
 * DynamicContent provides similar functionality to StaticContent rendering pre-defined HTML onto the page in place of
 * this component. However, it adds the ability to load or replace tags by id within the pre-defined HTML content and
 * utilize them at render time.
 *
 * @author Matt Hicks <mhicks@outr.com>
 */
trait DynamicContent extends Container[HTMLTag] with BodyChild with HTMLTag {
  def content: String
  def cached: Boolean = true

  private val dynamicContent = DynamicContent(content, cached)
  private var dynamicBlocks = Map.empty[String, DynamicHTMLBlock]

  /**
   * Loads the tag found within the content by id and returns as T.
   *
   * @param id to find the tag by
   * @tparam T the type of HTMLTag being returned
   * @return T
   */
  def load[T <: HTMLTag](id: String): T = synchronized {
    val dhb = dynamicContent.extract(id)
    val tag = HTMLTag.create(dhb.element.getName)
    tag.read(dhb.element)
    val block = dhb.copy(tag = tag)
    dynamicBlocks += id -> block
    contents += tag

    tag.asInstanceOf[T]
  }

  /**
   * Loads and then binds the tag (two-way) to the property and hierarchy defined. This allows a convenient way to
   * associate properties with dynamically defined fields.
   *
   * @param id to find the tag by
   * @param property to bind to
   * @param hierarchy name within the property to associate the binding (null represents a direct binding)
   * @param binder provides the bind implementation
   * @tparam T the HTMLTag type
   * @tparam V the value type for association
   * @return T
   */
  def bind[T <: HTMLTag, V](id: String, property: StandardProperty[_], hierarchy: String = null)(implicit binder: Binder[T, V]) = {
    Webpage().require(Realtime)   // Make sure we have realtime access
    val tag = load[T](id)
    binder.bind(tag, property, hierarchy)
    tag
  }

  /**
   * Replaces the tag found within the content by id with the one created by 'f'.
   *
   * @param id to find the tag by and replace with
   * @param f the function to generate the replacement
   * @tparam T the type of HTMLTag being generated to replace the existing content
   * @return T
   */
  def replace[T <: HTMLTag](id: String)(f: => T): T = synchronized {
    val dhb = dynamicContent.extract(id)
    val tag: T = f
    val block = dhb.copy(tag = tag)
    dynamicBlocks += id -> block
    contents += tag

    tag
  }

  def xmlLabel: String = "dynamiccontent"

  override protected def writeTag(writer: HTMLWriter) = writeBlocks(writer, dynamicContent.blocks)

  @tailrec
  private def writeBlocks(writer: HTMLWriter, blocks: List[HTMLBlock]): Unit = {
    if (blocks.nonEmpty) {
      blocks.head match {
        case shb: StaticHTMLBlock => writer.write(shb.content)
        case dhb: DynamicHTMLBlock => dynamicBlocks.get(dhb.id) match {
          case Some(b) => writer.write(b.content)
          case None => writer.write(dhb.content)
        }
      }
      writeBlocks(writer, blocks.tail)
    }
  }
}

object DynamicContent {
  val builder = new SAXBuilder()
  private var contents = Map.empty[String, DynamicHTML]

  def apply(content: String, cache: Boolean) = contents.get(content) match {
    case Some(dhtml) => dhtml
    case None => synchronized {
      val dhtml = new DynamicHTML(content)
      if (cache) {
        contents += content -> dhtml
      }
      dhtml
    }
  }
}

class DynamicHTML(content: String) {
  private var _blocks = List[HTMLBlock](new StaticHTMLBlock(content))

  def blocks = _blocks

  def extract(id: String) = synchronized {
    _blocks.collectFirst {
      case dhb: DynamicHTMLBlock if (dhb.id == id) => dhb
    }.headOption match {
      case Some(dhb) => dhb
      case None => findBlockWithId(id) match {
        case Some(block) => {
          val idIndex = block.content.indexOf("id=\"%s\"".format(id))
          val begin = block.content.lastIndexOf('<', idIndex)
          val end = findCloseIndex(begin, block.content)
          val content = block.content.substring(begin, end)
          val element = DynamicContent.builder.build(new StringReader(content)).getRootElement
          var newBlocks = List.empty[HTMLBlock]
          if (end < block.content.length) {
            newBlocks = StaticHTMLBlock(block.content.substring(end)) :: newBlocks
          }
          val dhb = DynamicHTMLBlock(id, element, content)
          newBlocks = dhb :: newBlocks
          if (begin > 0) {
            newBlocks = StaticHTMLBlock(block.content.substring(0, begin - 1)) :: newBlocks
          }
          _blocks = _blocks.patch(_blocks.indexOf(block), newBlocks, 1)
//          blocks = blocks.flatMap {
//            case b if (b == block) => newBlocks
//            case b => List(block)
//          }
          dhb
        }
        case None => throw new NullPointerException("Unable to find block with id: %s".format(id))
      }
    }
  }

  private def findBlockWithId(id: String) = _blocks.collectFirst {
    case shb: StaticHTMLBlock if (shb.content.indexOf("id=\"%s\"".format(id)) != -1) => shb
  }

  private def findCloseIndex(start: Int, content: String): Int = {
    var tagOpen = false
    var comment = false
    var quoteOpen = false
    var selfClosing = false
    var closingTag = false
    var open = 0
    var p = '_'
    var pp = '_'
    var ppp = '_'
    for (i <- start until content.length) {
      val c = content.charAt(i)
      if (comment) {
        if (c == '>' && p == '-' && pp == '-') {
          comment = false
        }
      } else if (tagOpen) {
        if (c == '"') {
          quoteOpen = !quoteOpen
        } else if (!quoteOpen && c == '-' && p == '-' && pp == '!' && ppp == '<') {
          open -= 1
          tagOpen = false
          comment = true
        } else if (c == '/' && !quoteOpen) {
          if (p == '<') {
            closingTag = true
          } else {
            selfClosing = true
          }
        } else if (c == '>' && !quoteOpen) {
          if (selfClosing) {
            open -= 1
            if (open == 0) {
              return i + 1
            }
          } else if (closingTag) {
            open -= 2
            if (open == 0) {
              return i + 1
            }
          }
          tagOpen = false
          selfClosing = false
          closingTag = false
        }
      } else if (c == '<') {
        open += 1
        tagOpen = true
      } else {
        // Ignore text
      }
      ppp = pp
      pp = p
      p = c
    }
    throw new RuntimeException("No closing tag found: %s".format(content.substring(start)))
  }
}

trait HTMLBlock {
  def content: String
}

case class StaticHTMLBlock(content: String) extends HTMLBlock

case class DynamicHTMLBlock(id: String, element: Element, original: String, tag: HTMLTag = null) extends HTMLBlock {
  def content = if (tag != null) {
    tag.outputString
  } else {
    original
  }
}

abstract class Binder[T <: HTMLTag, V](implicit manifest: Manifest[V]) {
  val valueProperty = new StandardProperty[V]("valueProperty")(parent = null, manifest = manifest)

  final def bind(tag: T, property: StandardProperty[_], hierarchy: String): Unit = {
    CaseClassBinding(property, hierarchy, valueProperty.asInstanceOf[StandardProperty[Any]])
    bind(tag)
    property.fireChanged()    // TODO: find a more efficient way to do this?
  }

  def bind(tag: T): Unit
}