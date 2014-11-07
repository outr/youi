package org.hyperscala.examples.ui

import com.outr.net.http.session.Session
import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.ui.jQueryUI
import org.hyperscala.realtime.Realtime
import org.hyperscala.ui.ConfirmDialog
import org.hyperscala.web._

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ConfirmDialogExample extends Example {
  this.require(Realtime)
  this.require(jQueryUI)
  connected[Webpage[Session]] {
    case webpage => Realtime.connectForm(webpage)
  }

  contents += new tag.P {
    contents += "ConfirmDialog offers an extension to jQuery UI's Dialog to provide simple true/false confirmation."
  }

  val button = new tag.Button(content = "Show Dialog")
  button.clickEvent.on {
    case evt => showDialog()
  }

  contents += button

  def showDialog() = {
    contents += new ConfirmDialog {
      dialog.title := "Are you sure?"
      dialog.modal := true
      def body = new tag.Div {
        contents += new tag.P(content = "This is an example confirmation dialog.")
        contents += new tag.P(content = "What will you choose?")
      }

      def confirm(result: Boolean) = println("RESULT: %s".format(result))
    }
  }
}
