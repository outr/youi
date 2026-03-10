package youi.capacitor

import scala.scalajs.js

object CapacitorPreferences {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Preferences")

  def set(key: String, value: String): js.Promise[Unit] =
    plugin.set(js.Dynamic.literal(key = key, value = value)).asInstanceOf[js.Promise[Unit]]

  def get(key: String): js.Promise[js.Dynamic] =
    plugin.get(js.Dynamic.literal(key = key)).asInstanceOf[js.Promise[js.Dynamic]]

  def remove(key: String): js.Promise[Unit] =
    plugin.remove(js.Dynamic.literal(key = key)).asInstanceOf[js.Promise[Unit]]

  def clear(): js.Promise[Unit] = plugin.clear().asInstanceOf[js.Promise[Unit]]

  def keys(): js.Promise[js.Dynamic] = plugin.keys().asInstanceOf[js.Promise[js.Dynamic]]
}
