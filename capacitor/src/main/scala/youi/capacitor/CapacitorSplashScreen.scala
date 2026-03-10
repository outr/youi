package youi.capacitor

import scala.scalajs.js

object CapacitorSplashScreen {
  private def plugin: js.Dynamic = Capacitor.Plugins.selectDynamic("SplashScreen")

  def show(options: js.UndefOr[js.Dynamic] = js.undefined): js.Promise[Unit] =
    (if (options.isDefined) plugin.show(options.get) else plugin.show()).asInstanceOf[js.Promise[Unit]]

  def hide(options: js.UndefOr[js.Dynamic] = js.undefined): js.Promise[Unit] =
    (if (options.isDefined) plugin.hide(options.get) else plugin.hide()).asInstanceOf[js.Promise[Unit]]
}
