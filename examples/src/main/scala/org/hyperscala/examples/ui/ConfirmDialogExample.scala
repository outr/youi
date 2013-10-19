package org.hyperscala.examples.ui

import org.hyperscala.html._
import org.hyperscala.web.Webpage
import org.hyperscala.jquery.ui.jQueryUI
import org.hyperscala.realtime.Realtime
import org.hyperscala.ui.ConfirmDialog
import org.hyperscala.examples.Example
import language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ConfirmDialogExample extends Example {
  Webpage().require(Realtime)
  Webpage().require(jQueryUI.Latest)
  Realtime.connectForm()

  val button = new tag.Button(content = "Show Dialog")
  button.clickEvent.on {
    case evt => showDialog()
  }

  contents += button

  def showDialog() = {
    contents += new ConfirmDialog {
      dialog.title := "Are you sure?"
      def body = new tag.Div {
        contents += new tag.P(content = "This is an example confirmation dialog.")
        contents += new tag.P(content = "What will you choose?")
      }

      def confirm(result: Boolean) = println("RESULT: %s".format(result))
    }
  }
}
