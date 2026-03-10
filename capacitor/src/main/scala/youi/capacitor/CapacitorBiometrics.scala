package youi.capacitor

import scala.scalajs.js

object CapacitorBiometrics {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("NativeBiometric")

  def checkBiometry(): js.Promise[js.Dynamic] = plugin.checkBiometry().asInstanceOf[js.Promise[js.Dynamic]]

  def authenticate(reason: js.UndefOr[String] = js.undefined, title: js.UndefOr[String] = js.undefined): js.Promise[Unit] = {
    val opts = js.Dynamic.literal()
    reason.foreach(v => opts.updateDynamic("reason")(v))
    title.foreach(v => opts.updateDynamic("title")(v))
    plugin.authenticate(opts).asInstanceOf[js.Promise[Unit]]
  }

  def setCredentials(server: String, username: String, password: String): js.Promise[Unit] =
    plugin.setCredentials(js.Dynamic.literal(server = server, username = username, password = password)).asInstanceOf[js.Promise[Unit]]

  def getCredentials(server: String): js.Promise[js.Dynamic] =
    plugin.getCredentials(js.Dynamic.literal(server = server)).asInstanceOf[js.Promise[js.Dynamic]]

  def deleteCredentials(server: String): js.Promise[Unit] =
    plugin.deleteCredentials(js.Dynamic.literal(server = server)).asInstanceOf[js.Promise[Unit]]
}
