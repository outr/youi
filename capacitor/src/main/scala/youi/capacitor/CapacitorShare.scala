package youi.capacitor

import scala.scalajs.js

object CapacitorShare {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Share")

  def canShare(): js.Promise[js.Dynamic] = plugin.canShare().asInstanceOf[js.Promise[js.Dynamic]]

  def share(
    title: js.UndefOr[String] = js.undefined,
    text: js.UndefOr[String] = js.undefined,
    url: js.UndefOr[String] = js.undefined,
    dialogTitle: js.UndefOr[String] = js.undefined,
    files: js.UndefOr[js.Array[String]] = js.undefined
  ): js.Promise[js.Dynamic] = {
    val opts = js.Dynamic.literal()
    title.foreach(v => opts.updateDynamic("title")(v))
    text.foreach(v => opts.updateDynamic("text")(v))
    url.foreach(v => opts.updateDynamic("url")(v))
    dialogTitle.foreach(v => opts.updateDynamic("dialogTitle")(v))
    files.foreach(v => opts.updateDynamic("files")(v))
    plugin.share(opts).asInstanceOf[js.Promise[js.Dynamic]]
  }
}
