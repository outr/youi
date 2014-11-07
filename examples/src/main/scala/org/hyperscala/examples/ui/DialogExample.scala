package org.hyperscala.examples.ui

import org.hyperscala.examples.Example
import org.hyperscala.html._
import org.hyperscala.jquery.ui._
import org.hyperscala.realtime.RealtimeEvent

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DialogExample extends Example {
  this.require(jQueryUI)

  contents += new tag.P {
    contents += "Dialog provides a simple wrapper around jQuery UI's Dialog."
  }

  val myDiv = new tag.Div(id = "myDiv", titleText = "Dialog Example") {
    contents += new tag.P(content = "This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.")
  }
  val myDialog = Dialog(myDiv)
  myDialog.title := "Hello World"
  myDialog.autoOpen := false
  myDialog.hide := Effect.Explode(duration = 1500, easing = Easing.EaseInBounce)
  myDialog.show := Effect.Fold
  contents += myDiv

  contents += new tag.Button(content = "Toggle Dialog Visible") {
    clickEvent := RealtimeEvent()

    clickEvent.on {
      case evt => myDialog.toggleOpen()
    }
  }
}