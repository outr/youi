package youi.paint

import youi.Color
import youi.drawable.Context

import scala.scalajs.js

case class ColorPaint(color: Color) extends Paint {
  override def asJS(context: Context): js.Any = color.toRGBA

  override def asCSS(): String = color.toRGBA

  override def toString: String = color.toString
}
