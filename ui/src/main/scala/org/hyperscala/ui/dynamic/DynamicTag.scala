package org.hyperscala.ui.dynamic

import org.hyperscala.html.HTMLTag
import org.jdom2.Element
import org.jdom2.input.SAXBuilder
import java.io.{File, StringReader}
import java.net.URL

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicTag[T <: HTMLTag] private(dynamicString: DynamicString, converter: String => String) extends DependentDynamicString(dynamicString, converter) {
  private var _element: Element = _
  def element = _element

  def create() = synchronized {
    checkUpdate()
    val tag = HTMLTag.create(element.getName).asInstanceOf[T]
    tag.read(element)
    tag
  }

  def apply(tag: HTMLTag) = synchronized {
    checkUpdate()
    tag.read(element)
  }

  override protected def refresh() {
    super.refresh()

    _element = DynamicTag.string2Element(content)
  }
}

object DynamicTag {
  import DynamicString._

  def from[T <: HTMLTag](content: String,
                         lastModified: Option[Long] = None,
                         converter: String => String = DynamicString.defaultConverter
                        ) = {
    val _content = content
    val _lastModified = lastModified
    new DynamicTag[T](new DynamicString {
      def lastModified: Long = _lastModified.getOrElse(0L)
      def content: String = _content
    }, converter)
  }

  def apply[T <: HTMLTag](name: String, dynamicString: DynamicString, converter: String => String = DynamicString.defaultConverter) = {
    getOrSet(name, new DynamicTag[T](dynamicString, converter))
  }

  def static[T <: HTMLTag](name: String, content: String, converter: String => String = DynamicString.defaultConverter) = {
    apply[T](name, DynamicString.static("%s.DynamicString".format(name), content, converter))
  }

  def dynamic[T <: HTMLTag](content: => String, converter: String => String = DynamicString.defaultConverter) = {
    new DynamicTag[T](DynamicString.dynamic(content, converter), DynamicString.defaultConverter)
  }

  def file[T <: HTMLTag](name: String, file: File, converter: String => String = DynamicString.defaultConverter) = {
    apply[T](name, DynamicString.file("%s.DynamicString".format(name), file, converter))
  }

  def url[T <: HTMLTag](name: String, url: URL, checkLastModified: Boolean = false, converter: String => String = DynamicString.defaultConverter) = {
    apply[T](name, DynamicString.url("%s.DynamicString".format(name), url, checkLastModified, converter))
  }

  private val conversions = Map(
    "&nbsp;" -> "&#160;",
    "&copy;" -> "&#169;"
  )

  def cleanup(html: String) = {
    var updated = html
    conversions.foreach {
      case (original, replacement) => updated = updated.replaceAll(original, replacement)
    }
    updated
  }

  def string2Element(html: String) = {
    val updated = cleanup(html)
    val builder = new SAXBuilder()
    builder.build(new StringReader(updated)).getRootElement
  }
}
