package com.outr.webframework.tags

import com.outr.webframework.{PreFormatted, WebContent}

import scala.xml.{Text => TextNode}
import com.outr.webframework.value.{StaticValue, Value}

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
case class Text(value: Value[String], preformat: Boolean = false) extends WebContent with PreFormatted {
  override def preFormatted = preformat match {
    case true => Some(value.value)
    case false => None
  }

  def xml = TextNode(preformat match {
    case true => uuid.toString
    case false => value.toString
  })
}

object Text {
  def apply(value: String): Text = Text(StaticValue(value))
}