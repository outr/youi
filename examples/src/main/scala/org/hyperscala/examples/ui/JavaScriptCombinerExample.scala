package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.realtime.Realtime
import org.hyperscala.ui.module.JavaScriptCombiner

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class JavaScriptCombinerExample extends Example {
  page.require(Realtime)
  page.require(JavaScriptCombiner)

  contents += "JavaScript sources are combined to return a single JavaScript file with all content."
}
