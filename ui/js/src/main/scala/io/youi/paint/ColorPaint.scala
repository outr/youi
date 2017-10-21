package io.youi.paint

import io.youi.Color
import io.youi.drawable.Context

import scala.scalajs.js

case class ColorPaint(color: Color) extends Paint {
  override def asJS(context: Context): js.Any = color.toRGBA
  override def toString: String = color.toString
}
