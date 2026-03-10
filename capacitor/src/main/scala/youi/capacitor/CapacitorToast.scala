package youi.capacitor

import scala.scalajs.js

object CapacitorToast {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Toast")

  def show(text: String, duration: String = "short", position: String = "bottom"): js.Promise[Unit] =
    plugin.show(js.Dynamic.literal(text = text, duration = duration, position = position)).asInstanceOf[js.Promise[Unit]]
}
