package org.hyperscala

import io.HTMLOutputter
import org.jdom2._
import java.io.OutputStream

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait XMLContent extends org.powerscala.hierarchy.Element {
  def toXML: Content
  def fromXML(content: Content): Unit
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

  def stream(out: OutputStream, outputter: HTMLOutputter = XMLContent.defaultOutputter) = {
    val writer = HTMLOutputter(out)
    outputter(toXML, writer)
    writer.close()
  }

  def outputString = {
    val b = new StringBuilder
    val writer = HTMLOutputter(b)
    val outputter = XMLContent.defaultOutputter
    outputter(toXML, writer)
    b.toString().trim
  }
}

object XMLContent {
  val defaultOutputter = new HTMLOutputter()
}