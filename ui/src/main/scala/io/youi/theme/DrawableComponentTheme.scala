package io.youi.theme

import io.youi.component.CanvasComponent
import io.youi.style.Paint
import reactify.Var

trait DrawableComponentTheme extends CanvasComponentTheme {
  override def defaultParent: Option[Theme] = Some(CanvasComponent)

  private def prnt[T](f: DrawableComponentTheme => T, default: => T): T = parent.collect {
    case p: DrawableComponentTheme => p
  }.map(f).getOrElse(default)

  val fill: Var[Paint] = Var(prnt(_.fill, Paint.none))
  val stroke: Var[Paint] = Var(prnt(_.stroke, Paint.none))
  val lineWidth: Var[Double] = Var(prnt(_.lineWidth, 1.0))
  val lineDash: Var[List[Double]] = Var(prnt(_.lineDash, Nil))
}
