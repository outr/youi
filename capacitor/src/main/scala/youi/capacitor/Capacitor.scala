package youi.capacitor

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("Capacitor")
object Capacitor extends js.Object {
  def isNativePlatform(): Boolean = js.native
  def getPlatform(): String = js.native
  def isPluginAvailable(name: String): Boolean = js.native
  def Plugins: js.Dynamic = js.native
}

@js.native
trait PluginListenerHandle extends js.Object {
  def remove(): js.Promise[Unit] = js.native
}
