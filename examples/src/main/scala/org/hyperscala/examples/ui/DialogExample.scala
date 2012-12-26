package org.hyperscala.examples.ui

import org.hyperscala.web.site.Webpage

import org.hyperscala.html._
import org.hyperscala.web.module.jQueryUI191
import org.hyperscala.jquery.ui._
import org.hyperscala.event.{ClickEvent, JavaScriptEvent}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class DialogExample extends Webpage {
  require(jQueryUI191)

  val myDialog = new tag.Div(id = "dialog", titleText = "Dialog Example") with Dialog {
    dialog.title := "Hello World"
    dialog.autoOpen := false
    dialog.hide := Effect.Explode(duration = 1500, easing = Easing.EaseInBounce)
    dialog.show := Effect.Fold

    contents += new tag.P(content = "This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.")
  }
  body.contents += myDialog

  body.contents += new tag.Button(content = "Toggle Dialog Visible") {
    event.click := JavaScriptEvent()

    listeners.synchronous {
      case evt: ClickEvent => myDialog.dialog.toggleOpen()
    }
  }
}