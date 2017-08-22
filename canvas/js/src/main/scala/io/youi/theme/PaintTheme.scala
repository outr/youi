package io.youi.theme

import io.youi.paint.Paint
import reactify.Var

trait PaintTheme {
  val fill: Var[Paint]
  val stroke: StrokeTheme
}
