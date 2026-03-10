package youi.capacitor

import scala.scalajs.js

object CapacitorKeyboard {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Keyboard")

  def show(): js.Promise[Unit] = plugin.show().asInstanceOf[js.Promise[Unit]]

  def hide(): js.Promise[Unit] = plugin.hide().asInstanceOf[js.Promise[Unit]]

  def setAccessoryBarVisible(isVisible: Boolean): js.Promise[Unit] =
    plugin.setAccessoryBarVisible(js.Dynamic.literal(isVisible = isVisible)).asInstanceOf[js.Promise[Unit]]

  def setResizeMode(mode: String): js.Promise[Unit] =
    plugin.setResizeMode(js.Dynamic.literal(mode = mode)).asInstanceOf[js.Promise[Unit]]

  def setScroll(isDisabled: Boolean): js.Promise[Unit] =
    plugin.setScroll(js.Dynamic.literal(isDisabled = isDisabled)).asInstanceOf[js.Promise[Unit]]

  def addListener(eventName: String)(callback: js.Dynamic => Unit): js.Promise[PluginListenerHandle] = {
    val fn: js.Function1[js.Dynamic, Unit] = callback
    plugin.addListener(eventName, fn).asInstanceOf[js.Promise[PluginListenerHandle]]
  }

  def removeAllListeners(): js.Promise[Unit] = plugin.removeAllListeners().asInstanceOf[js.Promise[Unit]]
}
