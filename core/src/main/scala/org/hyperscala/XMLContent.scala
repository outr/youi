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

  def stream(out: OutputStream, pretty: Boolean = true, outputter: HTMLOutputter = XMLContent.defaultOutputter) = {
    val writer = HTMLOutputter(out)
    outputter(toXML, writer)
    writer.close()
  }

  def outputString = {
    val b = new StringBuilder
    val writer = HTMLOutputter(b)
    val outputter = new HTMLOutputter("", "")
    outputter(toXML, writer)
    b.toString().trim
  }
}

object XMLContent {
  val defaultOutputter = new HTMLOutputter()
}