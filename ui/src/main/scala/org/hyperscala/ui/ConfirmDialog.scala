package org.hyperscala.ui

import org.hyperscala.html._
import constraints.BodyChild
import org.hyperscala.jquery.ui.Dialog
import org.hyperscala.web.Webpage
import language.reflectiveCalls
import com.outr.net.http.session.Session

/**
 * Displays a confirmation dialog that allows the user to accept or cancel.
 *
 * @author Matt Hicks <matt@outr.com>
 */
abstract class ConfirmDialog extends tag.Div {
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

  val dialog = Dialog(this)

  dialog.buttons := List(accept, cancel)

  contents += body

  dialog.buttonEvent.on {
    case evt => {
      dialog.close()
      confirm(evt.name == accept)
    }
  }
}

object ConfirmDialog {
  def show(webpage: Webpage, message: BodyChild, windowTitle: String = "Confirm", acceptText: String = "Accept", cancelText: String = "Cancel", modal: Boolean = true)(accepted: => Unit) = {
    val dialog = new ConfirmDialog {
      dialog.title := windowTitle
      dialog.modal := modal

      override def accept = acceptText
      override def cancel = cancelText

      def confirm(result: Boolean) = if (result) {
        accepted
      }

      def body = message
    }
    webpage.body.contents += dialog
  }
}