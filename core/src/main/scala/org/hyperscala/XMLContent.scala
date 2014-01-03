package org.hyperscala

import io.HTMLWriter
import org.jdom2._

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait XMLContent extends org.powerscala.hierarchy.Element[Any] {
  def write(writer: HTMLWriter): Unit
  def read(content: Content): Unit
  def render = true

  def removeFromParent() = {
    parent match {
      case container: Container[_] => {
        container.asInstanceOf[Container[XMLContent]].contents -= this
        true
      }
      case _ => false
    }
  }

  def replaceWith[T <: XMLContent](content: T) = {
    parent match {
      case container: Container[_] => {
        val c = container.asInstanceOf[Container[XMLContent]]
        val index = c.contents.indexOf(this)
        c.contents -= this
        c.contents.insert(index, content)
        content
      }
      case _ => throw new NullPointerException("No parent found for %s.".format(this))
    }
  }
}