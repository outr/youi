package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.javascript.JavaScriptString
import org.hyperscala.ui.module.jQueryEvents
import org.hyperscala.web._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jQueryEventsExample extends Webpage with Example {
  require(jQueryEvents)

  body.contents += "Utilize the jQueryEvents module to fire events via jQuery instead of inline."

  body.contents += new tag.Br
  body.contents += new tag.Br

  body.contents += new tag.Button(content = "Click Me") {
    clickEvent := JavaScriptString("alert('I have been clicked!');")
  }
}
