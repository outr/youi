package youi.material.impl

import scala.scalajs.js

@js.native
trait MDCDialogImplementation extends js.Object {
  def open(): Unit = js.native
  def close(action: String): Unit = js.native
  def isOpen: Boolean = js.native
}
