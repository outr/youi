package youi.capacitor

import scala.scalajs.js

object CapacitorStatusBar {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("StatusBar")

  def setStyle(style: String): js.Promise[Unit] =
    plugin.setStyle(js.Dynamic.literal(style = style)).asInstanceOf[js.Promise[Unit]]

  def setBackgroundColor(color: String): js.Promise[Unit] =
    plugin.setBackgroundColor(js.Dynamic.literal(color = color)).asInstanceOf[js.Promise[Unit]]

  def hide(): js.Promise[Unit] = plugin.hide().asInstanceOf[js.Promise[Unit]]

  def show(): js.Promise[Unit] = plugin.show().asInstanceOf[js.Promise[Unit]]

  def getInfo(): js.Promise[js.Dynamic] = plugin.getInfo().asInstanceOf[js.Promise[js.Dynamic]]

  def setOverlaysWebView(overlay: Boolean): js.Promise[Unit] =
    plugin.setOverlaysWebView(js.Dynamic.literal(overlay = overlay)).asInstanceOf[js.Promise[Unit]]
}
