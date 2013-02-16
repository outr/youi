package org.hyperscala.ui.dynamic

import org.hyperscala.html.HTMLTag
import org.jdom2.Element
import org.jdom2.input.SAXBuilder
import java.io.{File, StringReader}
import java.net.URL

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicTag[T <: HTMLTag] private(contentFunction: () => String, lastModifiedFunction: () => Long, converter: String => String) extends DynamicString(contentFunction, lastModifiedFunction, converter) {
  private var _element: Element = _
  def element = _element

  def create() = synchronized {
    refresh()
    val tag = HTMLTag.create(element.getName).asInstanceOf[T]
    tag.read(element)
    tag
  }

  override def modified(content: String) = {
    super.modified(content)
    _element = DynamicTag.string2Element(content)
  }
}

object DynamicTag {
  val builder = new SAXBuilder()

  import DynamicString._

  def apply[T <: HTMLTag](dynamicString: DynamicString, converter: String => String = DynamicString.defaultConverter) = {
    val name = "%s.dynamicTag"
    val wrappedConverter = (content: String) => converter(dynamicString.converter(content))
    getOrSet(name, new DynamicTag[T](dynamicString.contentFunction, dynamicString.lastModifiedFunction, wrappedConverter))
  }

  def static[T <: HTMLTag](name: String, content: String, converter: String => String = DynamicString.defaultConverter) = {
    getOrSet(name, new DynamicTag[T](contentFunction(content), defaultLastModifyFunction, converter))
  }

  def file[T <: HTMLTag](name: String, file: File, converter: String => String = DynamicString.defaultConverter) = {
    getOrSet(name, new DynamicTag[T](contentFunction(file), lastModifyFunction(file), converter))
  }

  def url[T <: HTMLTag](name: String, url: URL, checkLastModified: Boolean = false, converter: String => String = DynamicString.defaultConverter) = {
    getOrSet(name, new DynamicTag[T](contentFunction(url), lastModifyFunction(url, checkLastModified), converter))
  }

  def string2Element(html: String) = {
    builder.build(new StringReader(html)).getRootElement
  }
}
