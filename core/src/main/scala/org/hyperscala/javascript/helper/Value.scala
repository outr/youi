package com.outr.webframework.javascript.helper

import com.outr.webframework.WebContent
import xml.Text

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
case class Value(value: String) extends WebContent {
  def xml = Text("<%= " + value + " %>")
}
