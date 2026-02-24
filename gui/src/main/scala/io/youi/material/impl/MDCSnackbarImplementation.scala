package io.youi.material.impl

import scala.scalajs.js

@js.native
trait MDCSnackbarImplementation extends js.Object {
  def open(): Unit = js.native
  def close(reason: String): Unit = js.native
  var timeoutMs: Int = js.native
  var labelText: String = js.native
  def isOpen: Boolean = js.native
}
