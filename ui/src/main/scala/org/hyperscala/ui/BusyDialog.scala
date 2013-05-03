package org.hyperscala.ui

import org.hyperscala.html._
import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.site.{Website, Webpage}
import org.hyperscala.jquery.ui.Dialog
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
      dialog.width := 320
      dialog.height := 120
      dialog.resizable := false
      contents += new tag.Img(src = "/images/indeterminate_progress01.gif", width = "200", height = "40") {
        style.marginLeft = 40.px
      }
    }
  }

  def show(title: String) = {
    Webpage().body.byId[tag.Div with Dialog]("busyDialog") match {
      case Some(window) => {
        window.dialog.title := title
        if (!window.dialog.isOpen) {
          window.dialog.open()
        }
      }
      case None => // Page probably not loaded yet
    }
  }

  def hide() = {
    Webpage().body.byId[tag.Div with Dialog]("busyDialog") match {
      case Some(window) => {
        if (window.dialog.isOpen) {
          window.dialog.close()
        }
      }
      case None => // Page probably not loaded yet
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
