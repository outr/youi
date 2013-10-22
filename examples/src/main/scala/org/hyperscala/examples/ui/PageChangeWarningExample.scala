package org.hyperscala.examples.ui

import org.hyperscala.web.Webpage
import org.hyperscala.ui.PageChangeWarning

import org.hyperscala.html._
import org.hyperscala.examples.Example
import language.reflectiveCalls
import language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class PageChangeWarningExample extends Example {
  Webpage().require(PageChangeWarning)

  contents += new tag.Div {
    contents += new tag.A(href = "http://www.google.com", content = "Leave the Page")
    contents += new tag.Button(content = "Set Warning") {
      clickEvent := RealtimeEvent()

      clickEvent.on {
        case evt => PageChangeWarning.warn("There are unsaved changes.")
      }
    }

    contents += new tag.Button(content = "Clear Warning") {
      clickEvent := RealtimeEvent()

      clickEvent.on {
        case evt => PageChangeWarning.warn(null)
      }
    }
  }
}
