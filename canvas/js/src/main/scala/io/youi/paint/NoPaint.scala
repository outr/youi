package io.youi.paint

import io.youi.Context

import scala.scalajs.js

object NoPaint extends Paint {
  override def isEmpty = true

  override def asJS(context: Context): js.Any = ""

  override def toString: String = "NoPaint"
}
