package org.hyperscala.tags

import org.hyperscala.value.Property
import org.hyperscala.{WebContent, BodyContent, Container}
import org.hyperscala.js.JavaScriptContent

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class Head extends Container with BodyContent {
  object title extends Property[String] with WebContent {
    def xml = <title>{value}</title>
  }
  contents += title

  def +=(js: JavaScriptContent) = contents += new Script(content = js)

  def tag = "head"
}
