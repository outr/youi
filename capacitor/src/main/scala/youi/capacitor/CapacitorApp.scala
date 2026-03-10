package youi.capacitor

import scala.scalajs.js

object CapacitorApp {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("App")

  def getState(): js.Promise[js.Dynamic] = plugin.getState().asInstanceOf[js.Promise[js.Dynamic]]

  def exitApp(): js.Promise[Unit] = plugin.exitApp().asInstanceOf[js.Promise[Unit]]

  def getLaunchUrl(): js.Promise[js.Dynamic] = plugin.getLaunchUrl().asInstanceOf[js.Promise[js.Dynamic]]

  def addListener(eventName: String)(callback: js.Dynamic => Unit): js.Promise[PluginListenerHandle] = {
    val fn: js.Function1[js.Dynamic, Unit] = callback
    plugin.addListener(eventName, fn).asInstanceOf[js.Promise[PluginListenerHandle]]
  }

  def removeAllListeners(): js.Promise[Unit] = plugin.removeAllListeners().asInstanceOf[js.Promise[Unit]]
}
