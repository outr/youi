package youi.capacitor

import scala.scalajs.js

object CapacitorLocalNotifications {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("LocalNotifications")

  def schedule(notifications: js.Array[js.Dynamic]): js.Promise[js.Dynamic] =
    plugin.schedule(js.Dynamic.literal(notifications = notifications)).asInstanceOf[js.Promise[js.Dynamic]]

  def cancel(notifications: js.Array[js.Dynamic]): js.Promise[Unit] =
    plugin.cancel(js.Dynamic.literal(notifications = notifications)).asInstanceOf[js.Promise[Unit]]

  def getPending(): js.Promise[js.Dynamic] = plugin.getPending().asInstanceOf[js.Promise[js.Dynamic]]

  def registerActionTypes(types: js.Array[js.Dynamic]): js.Promise[Unit] =
    plugin.registerActionTypes(js.Dynamic.literal(types = types)).asInstanceOf[js.Promise[Unit]]

  def requestPermissions(): js.Promise[js.Dynamic] =
    plugin.requestPermissions().asInstanceOf[js.Promise[js.Dynamic]]

  def checkPermissions(): js.Promise[js.Dynamic] =
    plugin.checkPermissions().asInstanceOf[js.Promise[js.Dynamic]]

  def addListener(eventName: String)(callback: js.Dynamic => Unit): js.Promise[PluginListenerHandle] = {
    val fn: js.Function1[js.Dynamic, Unit] = callback
    plugin.addListener(eventName, fn).asInstanceOf[js.Promise[PluginListenerHandle]]
  }

  def removeAllListeners(): js.Promise[Unit] = plugin.removeAllListeners().asInstanceOf[js.Promise[Unit]]
}
