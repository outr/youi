package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.ui.BusyDialog
import org.hyperscala.html._
import org.hyperscala.realtime.RealtimeEvent
import org.hyperscala.jquery.Gritter
import org.hyperscala.jquery.ui.Dialog
import org.hyperscala.web._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class BusyDialogExample extends Example {
  this.require(BusyDialog)
  this.require(Gritter)

  contents += new tag.P {
    contents += "BusyDialog module expands on jQuery UI's Dialog to provide a convenient popup with an indeterminate progressbar in order to give a visual indication of a wait-state."
  }

  val div = new tag.Div(id = "helloDiv", content = "Hello World!")
  contents += div
  val dialog = Dialog(div)
  dialog.autoOpen := false

  contents += new tag.Button(content = "Wait for nothing...") {
    clickEvent := RealtimeEvent()
    clickEvent.on {
      case evt => BusyDialog(this.webpage, "Waiting for 5 seconds...") {
        Thread.sleep(5000)
        Gritter.add(this.webpage, "Finished Waiting", "That was a complete waste of time!")
      }
    }
  }
}
