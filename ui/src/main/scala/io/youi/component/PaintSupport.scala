package io.youi.component

import io.youi.component.draw.{Fill, Stroke}
import io.youi.style.Paint
import reactify.{Val, Var}

trait PaintSupport {
  protected def paintTheme: PaintTheme

  object fill {
    val paint: Var[Paint] = Var(paintTheme.fill)
    lazy val value: Val[Fill] = Val(Fill(paint))
  }
  object stroke {
    val paint: Var[Paint] = Var(paintTheme.stroke.paint)
    val lineWidth: Var[Double] = Var(paintTheme.stroke.lineWidth)
    val lineDash: Var[List[Double]] = Var(paintTheme.stroke.lineDash)
    lazy val value: Val[Stroke] = Val(Stroke(paint, lineWidth, lineDash))
  }
}

trait PaintTheme {
  val fill: Var[Paint]
  val stroke: StrokeTheme
}

trait StrokeTheme {
  val paint: Var[Paint]
  val lineWidth: Var[Double]
  val lineDash: Var[List[Double]]
}