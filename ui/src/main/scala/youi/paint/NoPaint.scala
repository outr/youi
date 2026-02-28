package youi.paint

import youi.drawable.Context

import scala.scalajs.js

object NoPaint extends Paint {
  override def isEmpty = true

  override def asJS(context: Context): js.Any = ""

  override def asCSS(): String = ""

  override def toString: String = "NoPaint"
}
