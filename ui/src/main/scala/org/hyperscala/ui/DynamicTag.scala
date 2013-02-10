package org.hyperscala.ui

import org.hyperscala.html.HTMLTag
import org.jdom2.Element
import org.jdom2.input.SAXBuilder
import java.io.{File, StringReader}
import org.powerscala.IO
import java.net.URL

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicTag[T <: HTMLTag] private(content: () => String, lastModified: () => Long) {
  private var currentModified: Long = _
  private var _element: Element = _
  def element = _element

  def create() = synchronized {
    refresh()
    val tag = HTMLTag.create(element.getName).asInstanceOf[T]
    tag.read(element)
    tag
  }

  def refresh(force: Boolean = false) = {
    val modified = lastModified()
    if (force || modified != currentModified) {
      val content = this.content()
      _element = DynamicTag.string2Element(content)
    }
    currentModified = modified
  }
}

object DynamicTag {
  private val builder = new SAXBuilder()
  private var map = Map.empty[String, DynamicTag[HTMLTag]]

  private def getOrSet[T <: HTMLTag](name: String, creator: => DynamicTag[T]) = synchronized {
    map.get(name) match {
      case Some(dt) => dt
      case None => {
        val dt: DynamicTag[T] = creator
        map += name -> dt.asInstanceOf[DynamicTag[HTMLTag]]
        dt
      }
    }
  }.asInstanceOf[DynamicTag[T]]

  def apply[T <: HTMLTag](name: String, content: String) = getOrSet[T](name, new DynamicTag[T](() => content, () => 0L))

  def apply[T <: HTMLTag](name: String, file: File) = getOrSet[T](name, new DynamicTag[T](() => IO.copy(file), () => file.lastModified()))

  def apply[T <: HTMLTag](name: String, url: URL, checkLastModified: Boolean = true) = {
    val lastModified = if (checkLastModified) {
      () => IO.lastModified(url)
    } else {
      val now = System.currentTimeMillis()
      () => now
    }
    getOrSet[T](name, new DynamicTag[T](() => IO.copy(url), lastModified))
  }

  def string2Element(html: String) = {
    builder.build(new StringReader(html)).getRootElement
  }
}
