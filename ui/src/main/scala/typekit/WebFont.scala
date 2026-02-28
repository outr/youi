package typekit

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("WebFont")
object WebFont extends js.Object {
  def load(configuration: WebFontConfiguration): Unit = js.native
}