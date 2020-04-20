package io.youi.paint

object NoPaint extends Paint {
  override def isEmpty = true

  override def asJS(context: Context): js.Any = ""

  override def asCSS(): String = ""

  override def toString: String = "NoPaint"
}
