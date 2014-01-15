package org.hyperscala.ui

import org.hyperscala.html._
import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Website, Webpage}
import org.hyperscala.jquery.ui.{jQueryUI, Dialog}
import org.hyperscala.realtime.Realtime
import language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object BusyDialog extends Module {
  def name = "busyDialog"

  def version = Version(1)

  override def dependencies = List(jQueryUI.LatestWithDefault, Realtime)

  def init() = {
    Website().register("/images/indeterminate_progress01.gif", "indeterminate_progress01.gif")
  }

  def disabled[T](f: => T): T = {
    disable()
    try {
      f
    } finally {
      enable()
    }
  }

  def enable() = {
    Webpage().store.remove("BusyDialog.disabled")
  }

  def disable() = {
    hide()
    Webpage().store("BusyDialog.disabled") = true
  }

  def isDisabled = Webpage().store.getOrElse("BusyDialog.disabled", false)

  def load() = {
    val div = new tag.Div(id = "busyDialog") {
      contents += new tag.Img(src = "/images/indeterminate_progress01.gif", width = "200", height = "40") {
        style.marginLeft := 40.px
      }
    }
    Webpage().body.contents += div

    val dialog = Dialog(div)
    dialog.autoOpen := false
    dialog.closeOnEscape := false
    dialog.modal := true
    dialog.width := 320
    dialog.height := 120
    dialog.resizable := false
  }

  def show(title: String) = if (!isDisabled) {
    Webpage().body.byId[tag.Div with Dialog]("busyDialog") match {
      case Some(window) => {
        Dialog(window).title := title
        if (!Dialog(window).isOpen()) {
          Dialog(window).open()
        }
      }
      case None => // Page probably not loaded yet
    }
  }

  def hide() = if (!isDisabled) {
    Webpage().body.byId[tag.Div]("busyDialog") match {
      case Some(window) => {
        if (Dialog(window).isOpen()) {
          Dialog(window).close()
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
