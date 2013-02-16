package org.hyperscala.ui.dynamic

import org.powerscala.IO
import java.io.File
import java.net.URL

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicString protected(val contentFunction: () => String, val lastModifiedFunction: () => Long, val converter: String => String) {
  private var _lastModified: Long = _
  private var _content: String = _

  refresh(force = true)

  def name = DynamicString.name(this)

  def lastModified = {
    refresh()
    _lastModified
  }
  def content = {
    refresh()
    _content
  }

  def set(content: String, lastModified: Long = System.currentTimeMillis()) = synchronized {
    _content = content
    _lastModified = lastModified
    modified(content)
  }

  def refresh(force: Boolean = false): Unit = {
    val modified = lastModifiedFunction()
    if (force || modified > _lastModified) {
      val content = contentFunction()
      val converted = converter(content)
      set(converted, modified)
    }
  }

  protected def modified(content: String) = {}
}

object DynamicString {
  private var map = Map.empty[String, DynamicString]

  private def name(dynamicString: DynamicString) = map.collectFirst {
    case (name, ds) if (ds == dynamicString) => name
  }.getOrElse(throw new NullPointerException("Unable to find name for %s.".format(dynamicString)))

  def getOrSet[T <: DynamicString](name: String, creator: => T): T = synchronized {
    map.get(name) match {
      case Some(ds) => ds.asInstanceOf[T]
      case None => {
        val ds: T = creator
        map += name -> ds.asInstanceOf[DynamicString]
        ds
      }
    }
  }

  val defaultConverter = (s: String) => s

  def static(name: String, content: String, converter: String => String = defaultConverter) = {
    getOrSet(name, new DynamicString(contentFunction(content), defaultLastModifyFunction, converter))
  }
  def file(name: String, file: File, converter: String => String = defaultConverter) = {
    getOrSet(name, new DynamicString(contentFunction(file), lastModifyFunction(file), converter))
  }
  def url(name: String, url: URL, checkLastModified: Boolean = false, converter: String => String = defaultConverter) = {
    getOrSet(name, new DynamicString(contentFunction(url), lastModifyFunction(url, checkLastModified), converter))
  }

  def contentFunction(content: String) = () => content
  def contentFunction(file: File) = () => IO.copy(file)
  def contentFunction(url: URL) = () => IO.copy(url)

  val defaultLastModifyFunction = {
    val initial = System.currentTimeMillis()
    () => initial
  }
  def lastModifyFunction(file: File) = () => file.lastModified()
  def lastModifyFunction(url: URL, checkLastModified: Boolean = false) = if (checkLastModified) {
    () => IO.lastModified(url)
  } else {
    defaultLastModifyFunction
  }
}