package org.hyperscala.ui

import com.outr.net.http.session.Session
import org.hyperscala.html._
import org.hyperscala.jquery.ui.{Dialog, ProgressBar, jQueryUI}
import org.hyperscala.module.Module
import org.hyperscala.realtime.Realtime
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.Version

import scala.language.reflectiveCalls

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
object BusyDialog extends Module {
  def name = "busyDialog"

  def version = Version(1)

  override def dependencies = List(Realtime, jQueryUI)

  override def init(website: Website) = {
  }

  override def load(webpage: Webpage) = {
    val div = new BusyDialog
    webpage.body.contents += div

    Dialog.assign(div, autoOpen = false, closeOnEscape = false, modal = true, width = 320, height = 120, resizable = false)
  }

  def disabled[T, S <: Session](webpage: Webpage)(f: => T): T = {
    disable(webpage)
    try {
      f
    } finally {
      enable(webpage)
    }
  }

  def enable(webpage: Webpage) = {
    webpage.store.remove("BusyDialog.disabled")
  }

  def disable(webpage: Webpage) = {
    hide(webpage)
    webpage.store("BusyDialog.disabled") = true
  }

  def isDisabled(webpage: Webpage) = webpage.store.getOrElse("BusyDialog.disabled", false)

  def show(webpage: Webpage, title: String, progress: Option[Double] = None) = if (!isDisabled(webpage)) {
    webpage.body.byId[BusyDialog]("busyDialog") match {
      case Some(window) => {
        Dialog(window).title := title
        window.progressBar.value := progress.map(d => math.round(d * 100.0).toInt)
        if (!Dialog(window).isOpen()) {
          Dialog(window).open()
        }
      }
      case None => // Page probably not loaded yet
    }
  }

  def hide(webpage: Webpage) = if (!isDisabled(webpage)) {
    webpage.body.byId[tag.Div]("busyDialog") match {
      case Some(window) => {
        if (Dialog(window).isOpen()) {
          Dialog(window).close()
        }
      }
      case None => // Page probably not loaded yet
    }
  }

  def apply[T, S <: Session](webpage: Webpage, title: String, progress: Option[Double] = None)(f: => T): T = {
    BusyDialog.show(webpage, title, progress)
    try {
      f
    } finally {
      BusyDialog.hide(webpage)
    }
  }
}

class BusyDialog extends tag.Div(id = "busyDialog") {
  val progressDiv = new tag.Div {
    style.marginTop := 10.px
    style.width := 280.px
    style.height := 30.px
  }
  val progressBar = ProgressBar(progressDiv)
  progressBar.value := None
  contents += progressDiv
}