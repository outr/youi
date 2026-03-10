package youi.capacitor

import scala.scalajs.js

object CapacitorPushNotifications {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("PushNotifications")

  def requestPermissions(): js.Promise[js.Dynamic] =
    plugin.requestPermissions().asInstanceOf[js.Promise[js.Dynamic]]

  def register(): js.Promise[Unit] = plugin.register().asInstanceOf[js.Promise[Unit]]

  def getDeliveredNotifications(): js.Promise[js.Dynamic] =
    plugin.getDeliveredNotifications().asInstanceOf[js.Promise[js.Dynamic]]

  def removeAllDeliveredNotifications(): js.Promise[Unit] =
    plugin.removeAllDeliveredNotifications().asInstanceOf[js.Promise[Unit]]

  def createChannel(channel: js.Dynamic): js.Promise[Unit] =
    plugin.createChannel(channel).asInstanceOf[js.Promise[Unit]]

  def addListener(eventName: String)(callback: js.Dynamic => Unit): js.Promise[PluginListenerHandle] = {
    val fn: js.Function1[js.Dynamic, Unit] = callback
    plugin.addListener(eventName, fn).asInstanceOf[js.Promise[PluginListenerHandle]]
  }

  def removeAllListeners(): js.Promise[Unit] = plugin.removeAllListeners().asInstanceOf[js.Promise[Unit]]
}
