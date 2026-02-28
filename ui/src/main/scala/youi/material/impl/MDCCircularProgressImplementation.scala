package youi.material.impl

import scala.scalajs.js

@js.native
trait MDCCircularProgressImplementation extends js.Object {
  var determinate: Boolean = js.native
  var progress: Double = js.native
  def open(): Unit = js.native
  def close(): Unit = js.native
}
