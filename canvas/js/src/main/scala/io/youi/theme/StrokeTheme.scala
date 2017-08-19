package io.youi.theme

import io.youi.paint.{LineCap, LineJoin, Paint}
import reactify._

trait StrokeTheme {
  val paint: Var[Paint]
  val lineWidth: Var[Double]
  val lineDash: Var[List[Double]]
  val lineDashOffset: Var[Double]
  val lineCap: Var[LineCap]
  val lineJoin: Var[LineJoin]
}