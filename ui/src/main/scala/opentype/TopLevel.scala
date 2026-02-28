package opentype

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("opentype")
object TopLevel extends js.Object {
  def load(url: String, callback: js.Function2[String, Font, Unit]): Unit = js.native
}