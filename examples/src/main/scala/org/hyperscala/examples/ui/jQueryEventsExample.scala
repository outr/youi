package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.ui.module.jQueryEvents

import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptString

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jQueryEventsExample extends Example {
  page.require(jQueryEvents)

  contents += "Utilize the jQueryEvents module to fire events via jQuery instead of inline."

  contents += new tag.Br
  contents += new tag.Br

  contents += new tag.Button(content = "Click Me") {
    clickEvent := JavaScriptString("alert('I have been clicked!');")
  }
}
