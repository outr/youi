package youi.capacitor

import scala.scalajs.js

object CapacitorGeolocation {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Geolocation")

  def getCurrentPosition(enableHighAccuracy: Boolean = true, timeout: Int = 10000): js.Promise[js.Dynamic] =
    plugin.getCurrentPosition(js.Dynamic.literal(
      enableHighAccuracy = enableHighAccuracy,
      timeout = timeout
    )).asInstanceOf[js.Promise[js.Dynamic]]

  def watchPosition(enableHighAccuracy: Boolean = true)(callback: js.Dynamic => Unit): js.Promise[String] = {
    val fn: js.Function2[js.Dynamic, js.Dynamic, Unit] = (pos, err) => callback(pos)
    plugin.watchPosition(js.Dynamic.literal(enableHighAccuracy = enableHighAccuracy), fn).asInstanceOf[js.Promise[String]]
  }

  def clearWatch(id: String): js.Promise[Unit] =
    plugin.clearWatch(js.Dynamic.literal(id = id)).asInstanceOf[js.Promise[Unit]]

  def checkPermissions(): js.Promise[js.Dynamic] = plugin.checkPermissions().asInstanceOf[js.Promise[js.Dynamic]]

  def requestPermissions(): js.Promise[js.Dynamic] = plugin.requestPermissions().asInstanceOf[js.Promise[js.Dynamic]]
}
