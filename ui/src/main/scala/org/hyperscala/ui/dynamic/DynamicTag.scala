package org.hyperscala.ui.dynamic

import org.hyperscala.html.HTMLTag
import org.jdom2.Element
import org.jdom2.input.SAXBuilder
import java.io.{File, StringReader}
import java.net.URL

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicTag[T <: HTMLTag] private(contentFunction: () => String, lastModifiedFunction: () => Long) extends DynamicString(contentFunction, lastModifiedFunction) {
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

  def apply[T <: HTMLTag](name: String, content: String) = {
    getOrSet(name, new DynamicTag[T](contentFunction(content), defaultLastModifyFunction))
  }

  def apply[T <: HTMLTag](name: String, file: File) = {
    getOrSet(name, new DynamicTag[T](contentFunction(file), lastModifyFunction(file)))
  }

  def apply[T <: HTMLTag](name: String, url: URL, checkLastModified: Boolean = false) = {
    getOrSet(name, new DynamicTag[T](contentFunction(url), lastModifyFunction(url, checkLastModified)))
  }

  def string2Element(html: String) = {
    builder.build(new StringReader(html)).getRootElement
  }
}
