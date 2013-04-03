package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.examples.Example
import org.hyperscala.jquery.ui.Tabs

/**
 * @author Matt Hicks <matt@outr.com>
 */
class TabsExample extends Example {
  contents += new Tabs {
    id := "tabsExample"

    val first = addTab("First") {
      new tag.Div {
        contents += "This is the first tab!"
      }
    }
    val second = addTab("Second") {
      new tag.Div {
        contents += "This is the second tab!"
      }
    }
    val third = addTab("Third") {
      new tag.Div {
        contents += "This is the third tab!"
      }
    }
  }
}
