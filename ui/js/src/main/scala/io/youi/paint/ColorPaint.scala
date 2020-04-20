package io.youi.paint

case class ColorPaint(color: Color) extends Paint {
  override def asJS(context: Context): js.Any = color.toRGBA

  override def asCSS(): String = color.toRGBA

  override def toString: String = color.toString
}
