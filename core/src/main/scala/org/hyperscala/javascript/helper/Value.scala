package org.hyperscala.javascript.helper

import org.hyperscala.WebContent
import xml.Text

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
case class Value(value: String) extends WebContent {
  def xml = Text("<%= " + value + " %>")
}
