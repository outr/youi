package com.outr.webframework.tags

import com.outr.webframework.value.Property
import com.outr.webframework.{WebContent, BodyContent, Container}
import com.outr.webframework.js.JavaScriptContext

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class Head extends Container with BodyContent {
  object title extends Property[String] with WebContent {
    def xml = <title>{value}</title>
  }
  contents += title

  def +=(js: JavaScriptContext) = contents += new Script(content = js)

  def tag = "head"
}
