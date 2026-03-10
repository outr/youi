package youi.capacitor

import scala.scalajs.js

object CapacitorClipboard {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Clipboard")

  def write(string: js.UndefOr[String] = js.undefined, url: js.UndefOr[String] = js.undefined, image: js.UndefOr[String] = js.undefined): js.Promise[Unit] = {
    val opts = js.Dynamic.literal()
    string.foreach(v => opts.updateDynamic("string")(v))
    url.foreach(v => opts.updateDynamic("url")(v))
    image.foreach(v => opts.updateDynamic("image")(v))
    plugin.write(opts).asInstanceOf[js.Promise[Unit]]
  }

  def read(): js.Promise[js.Dynamic] = plugin.read().asInstanceOf[js.Promise[js.Dynamic]]
}
