package io.youi.theme

import io.youi.Color
import io.youi.component.Component
import io.youi.paint.Paint
import reactify.Var

trait BasicTextTheme extends ComponentTheme {
  override def defaultParent: Option[Theme] = Some(Component)

  private def prnt[T](f: BasicTextTheme => T, default: => T): T = parent().collect {
    case p: BasicTextTheme => p
  }.map(f).getOrElse(default)

  val breakWords: Var[Boolean] = Var(prnt(_.breakWords, false))
  object dropShadow {
    val enabled: Var[Boolean] = Var(prnt(_.dropShadow.enabled, false))
    val angle: Var[Double] = Var(prnt(_.dropShadow.angle, 0.0))
    val blur: Var[Double] = Var(prnt(_.dropShadow.blur, 0.0))
    val color: Var[Color] = Var(prnt(_.dropShadow.color, Color.Black))
    val distance: Var[Double] = Var(prnt(_.dropShadow.distance, 0.0))
  }
  val fill: Var[Paint] = Var(prnt(_.fill, Paint.none))
  object font {
    val family: Var[String] = Var(prnt(_.font.family, "Arial"))
    val size: Var[Double] = Var(prnt(_.font.size, 26.0))
    val style: Var[String] = Var(prnt(_.font.style, "normal"))
    val variant: Var[String] = Var(prnt(_.font.variant, "normal"))
    val weight: Var[String] = Var(prnt(_.font.weight, "normal"))
  }
  val letterSpacing: Var[Double] = Var(prnt(_.letterSpacing, 0.0))
  val lineHeight: Var[Double] = Var(prnt(_.lineHeight, 0.0))
  val lineJoin: Var[String] = Var(prnt(_.lineJoin, "miter"))
  val miterLimit: Var[Double] = Var(prnt(_.miterLimit, 10.0))
  val padding: Var[Double] = Var(prnt(_.padding, 0.0))
  val stroke: Var[Color] = Var(prnt(_.stroke, Color.Clear))
  val strokeThickness: Var[Double] = Var(prnt(_.strokeThickness, 0.0))
  val textBaseline: Var[String] = Var(prnt(_.textBaseline, "alphabetic"))
  val wordWrap: Var[Boolean] = Var(prnt(_.wordWrap, false))
}