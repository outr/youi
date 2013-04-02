package org.hyperscala.ui

import org.hyperscala.html._
import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.site.{Website, Webpage}
import org.hyperscala.jquery.ui.Dialog
import org.hyperscala.css.attributes.Length
import org.hyperscala.realtime.Realtime
import language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object BusyDialog extends Module {
  def name = "busyDialog"

  def version = Version(1)

  override def dependencies = List(Realtime)

  def init() = {
    Website().register("/images/indeterminate_progress01.gif", "indeterminate_progress01.gif")
  }

  def load() = {
    Webpage().body.contents += new tag.Div(id = "busyDialog") with Dialog {
      dialog.autoOpen := false
      dialog.closeOnEscape := false
      dialog.modal := true
      dialog.width := 240
      dialog.height := 120
      dialog.resizable := false
      contents += new tag.Img(src = "/images/indeterminate_progress01.gif", width = "200", height = "40") {
        style.marginLeft = Length.Auto
        style.marginRight = Length.Auto
      }
    }
  }

  def show(title: String) = {
    val window = Webpage().body.getById[tag.Div with Dialog]("busyDialog")
    window.dialog.title := title
    if (!window.dialog.isOpen) {
      window.dialog.open()
    }
  }

  def hide() = {
    val window = Webpage().body.getById[tag.Div with Dialog]("busyDialog")
    if (window.dialog.isOpen) {
      window.dialog.close()
    }
  }

  def apply[T](title: String)(f: => T): T = {
    BusyDialog.show(title)
    try {
      f
    } finally {
      BusyDialog.hide()
    }
  }
}
