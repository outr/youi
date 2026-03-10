package youi.capacitor

import scala.scalajs.js

object CapacitorScreenOrientation {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("ScreenOrientation")

  def lock(orientation: String): js.Promise[Unit] =
    plugin.lock(js.Dynamic.literal(orientation = orientation)).asInstanceOf[js.Promise[Unit]]

  def unlock(): js.Promise[Unit] = plugin.unlock().asInstanceOf[js.Promise[Unit]]

  def getOrientation(): js.Promise[js.Dynamic] = plugin.orientation().asInstanceOf[js.Promise[js.Dynamic]]

  def addListener(eventName: String)(callback: js.Dynamic => Unit): js.Promise[PluginListenerHandle] = {
    val fn: js.Function1[js.Dynamic, Unit] = callback
    plugin.addListener(eventName, fn).asInstanceOf[js.Promise[PluginListenerHandle]]
  }
}
