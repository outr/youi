package youi.capacitor

import scala.scalajs.js

object CapacitorBrowser {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("Browser")

  def open(url: String, windowName: js.UndefOr[String] = js.undefined, toolbarColor: js.UndefOr[String] = js.undefined): js.Promise[Unit] = {
    val opts = js.Dynamic.literal(url = url)
    windowName.foreach(v => opts.updateDynamic("windowName")(v))
    toolbarColor.foreach(v => opts.updateDynamic("toolbarColor")(v))
    plugin.open(opts).asInstanceOf[js.Promise[Unit]]
  }

  def close(): js.Promise[Unit] = plugin.close().asInstanceOf[js.Promise[Unit]]

  def addListener(eventName: String)(callback: js.Dynamic => Unit): js.Promise[PluginListenerHandle] = {
    val fn: js.Function1[js.Dynamic, Unit] = callback
    plugin.addListener(eventName, fn).asInstanceOf[js.Promise[PluginListenerHandle]]
  }

  def removeAllListeners(): js.Promise[Unit] = plugin.removeAllListeners().asInstanceOf[js.Promise[Unit]]
}
