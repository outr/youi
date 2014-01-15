package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.ui.BusyDialog
import org.hyperscala.html._
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.jquery.Gritter
import org.hyperscala.jquery.ui.Dialog

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BusyDialogExample extends Example {
  page.require(BusyDialog)
  page.require(Gritter)

  val div = new tag.Div(id = "helloDiv", content = "Hello World!")
  contents += div
  val dialog = Dialog(div)
  dialog.autoOpen := false

  contents += new tag.Button(content = "Wait for nothing...") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => BusyDialog("Waiting for 5 seconds...") {
        Thread.sleep(5000)
        Gritter.add("Finished Waiting", "That was a complete waste of time!")
      }
    }
  }
  contents += new tag.Button(content = "Show Hello") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => dialog.open()
    }
  }
}
