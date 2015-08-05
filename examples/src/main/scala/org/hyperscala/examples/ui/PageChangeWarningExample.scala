package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.ui.PageChangeWarning
import org.hyperscala.web._

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class PageChangeWarningExample extends Webpage with Example {
  require(PageChangeWarning)

  body.contents += new tag.Div {
    contents += new tag.A(href = "http://www.google.com", content = "Leave the Page")
    contents += new tag.Button(content = "Set Warning") {
      clickEvent := RealtimeEvent()

      clickEvent.on {
        case evt => PageChangeWarning.warn(this.webpage, "There are unsaved changes.")
      }
    }

    contents += new tag.Button(content = "Clear Warning") {
      clickEvent := RealtimeEvent()

      clickEvent.on {
        case evt => PageChangeWarning.warn(this.webpage, null)
      }
    }
  }
}
