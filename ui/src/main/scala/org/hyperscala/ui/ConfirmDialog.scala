package org.hyperscala.ui

import org.hyperscala.html._
import constraints.BodyChild
import org.hyperscala.jquery.ui.{DialogButtons, Dialog}
import org.hyperscala.jquery.ui.event.ButtonClicked
import org.hyperscala.web.site.Webpage
import language.reflectiveCalls

/**
 * Displays a confirmation dialog that allows the user to accept or cancel.
 *
 * @author Matt Hicks <matt@outr.com>
 */
abstract class ConfirmDialog extends tag.Div with Dialog {
  /**
   * Body of this dialog.
   */
  def body: BodyChild

  def accept: String = "Accept"
  def cancel: String = "Cancel"

  /**
   * This method is invoked on confirm or cancel.
   */
  def confirm(result: Boolean): Unit
  def singleUse = true

  dialog.buttons := DialogButtons(accept, cancel)

  contents += body

  listeners.synchronous {
    case evt: ButtonClicked => {
      dialog.close()
      confirm(evt.name == accept)
    }
  }
}

object ConfirmDialog {
  def show(message: BodyChild, windowTitle: String = "Confirm", acceptText: String = "Accept", cancelText: String = "Cancel")(accepted: => Unit) = {
    val dialog = new ConfirmDialog {
      dialog.title := windowTitle

      override def accept = acceptText
      override def cancel = cancelText

      def confirm(result: Boolean) = if (result) {
        accepted
      }

      def body = message
    }
    Webpage().body.contents += dialog
  }
}