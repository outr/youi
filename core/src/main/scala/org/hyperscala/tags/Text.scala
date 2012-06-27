package org.hyperscala.tags

import org.hyperscala.{PreFormatted, WebContent}

import scala.xml.{Text => TextNode}
import org.hyperscala.value.{StaticValue, Value}

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
case class Text(value: Value[String]) extends WebContent with PreFormatted {
  override def preFormatted = Some(value.value)

  def xml = TextNode(uuid.toString)
}

object Text {
  def apply(value: String): Text = Text(StaticValue(value))
}