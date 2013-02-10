package org.hyperscala.ui.dynamic

import org.powerscala.IO
import java.io.File
import java.net.URL

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class DynamicString(contentFunction: () => String, lastModifiedFunction: () => Long) {
  private var _lastModified: Long = _
  private var _content: String = _

  refresh(force = true)

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
      set(contentFunction(), modified)
    }
  }

  protected def modified(content: String) = {}
}

object DynamicString {
  private var map = Map.empty[String, DynamicString]

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

  def apply(name: String, content: String) = {
    getOrSet(name, new DynamicString(contentFunction(content), defaultLastModifyFunction))
  }
  def apply(name: String, file: File) = {
    getOrSet(name, new DynamicString(contentFunction(file), lastModifyFunction(file)))
  }
  def apply(name: String, url: URL, checkLastModified: Boolean = false) = {
    getOrSet(name, new DynamicString(contentFunction(url), lastModifyFunction(url, checkLastModified)))
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