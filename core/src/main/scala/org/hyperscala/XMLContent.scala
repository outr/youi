package org.hyperscala

import io.HTMLWriter
import org.jdom2._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait XMLContent extends org.powerscala.hierarchy.Element {
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
}