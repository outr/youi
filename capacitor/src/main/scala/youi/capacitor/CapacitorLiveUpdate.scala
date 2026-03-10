package youi.capacitor

import scala.scalajs.js

object CapacitorLiveUpdate {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("LiveUpdate")

  def sync(): js.Promise[js.Dynamic] = plugin.sync().asInstanceOf[js.Promise[js.Dynamic]]

  def ready(): js.Promise[Unit] = plugin.ready().asInstanceOf[js.Promise[Unit]]

  def reload(): js.Promise[Unit] = plugin.reload().asInstanceOf[js.Promise[Unit]]

  def reset(): js.Promise[Unit] = plugin.reset().asInstanceOf[js.Promise[Unit]]

  def getChannel(): js.Promise[js.Dynamic] = plugin.getChannel().asInstanceOf[js.Promise[js.Dynamic]]

  def setChannel(channel: String): js.Promise[Unit] =
    plugin.setChannel(js.Dynamic.literal(channel = channel)).asInstanceOf[js.Promise[Unit]]

  def getCurrentBundle(): js.Promise[js.Dynamic] = plugin.getCurrentBundle().asInstanceOf[js.Promise[js.Dynamic]]

  def getNextBundle(): js.Promise[js.Dynamic] = plugin.getNextBundle().asInstanceOf[js.Promise[js.Dynamic]]
}
