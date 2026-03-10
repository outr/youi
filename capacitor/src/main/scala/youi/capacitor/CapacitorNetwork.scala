package youi.capacitor

import scala.scalajs.js

object CapacitorNetwork {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Network")

  def getStatus(): js.Promise[js.Dynamic] = plugin.getStatus().asInstanceOf[js.Promise[js.Dynamic]]

  def addListener(eventName: String)(callback: js.Dynamic => Unit): js.Promise[PluginListenerHandle] = {
    val fn: js.Function1[js.Dynamic, Unit] = callback
    plugin.addListener(eventName, fn).asInstanceOf[js.Promise[PluginListenerHandle]]
  }

  def removeAllListeners(): js.Promise[Unit] = plugin.removeAllListeners().asInstanceOf[js.Promise[Unit]]
}
