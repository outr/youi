package youi.capacitor

import scala.scalajs.js

object CapacitorHaptics {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Haptics")

  def impact(style: String = "medium"): js.Promise[Unit] =
    plugin.impact(js.Dynamic.literal(style = style)).asInstanceOf[js.Promise[Unit]]

  def notification(`type`: String = "success"): js.Promise[Unit] =
    plugin.notification(js.Dynamic.literal(`type` = `type`)).asInstanceOf[js.Promise[Unit]]

  def vibrate(duration: Int = 300): js.Promise[Unit] =
    plugin.vibrate(js.Dynamic.literal(duration = duration)).asInstanceOf[js.Promise[Unit]]

  def selectionStart(): js.Promise[Unit] = plugin.selectionStart().asInstanceOf[js.Promise[Unit]]
  def selectionChanged(): js.Promise[Unit] = plugin.selectionChanged().asInstanceOf[js.Promise[Unit]]
  def selectionEnd(): js.Promise[Unit] = plugin.selectionEnd().asInstanceOf[js.Promise[Unit]]
}
