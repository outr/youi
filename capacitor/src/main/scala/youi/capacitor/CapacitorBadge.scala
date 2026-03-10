package youi.capacitor

import scala.scalajs.js

object CapacitorBadge {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Badge")

  def set(count: Int): js.Promise[Unit] =
    plugin.set(js.Dynamic.literal(count = count)).asInstanceOf[js.Promise[Unit]]

  def get(): js.Promise[js.Dynamic] = plugin.get().asInstanceOf[js.Promise[js.Dynamic]]

  def clear(): js.Promise[Unit] = plugin.clear().asInstanceOf[js.Promise[Unit]]

  def isSupported(): js.Promise[js.Dynamic] = plugin.isSupported().asInstanceOf[js.Promise[js.Dynamic]]
}
