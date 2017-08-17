package io.youi.theme

import io.youi.Color
import io.youi.component.Component
import io.youi.paint.{Paint, Stroke}
import reactify.Var

trait BasicTextTheme extends ComponentTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)

  private def prnt[T](f: BasicTextTheme => T, default: => T): T = parentTheme().collect {
    case p: BasicTextTheme => p
  }.map(f).getOrElse(default)

  val breakWords: Var[Boolean] = prop(prnt(_.breakWords, false), updatesRendering = true)
  object dropShadow {
    val enabled: Var[Boolean] = prop(prnt(_.dropShadow.enabled, false), updatesRendering = true)
    val angle: Var[Double] = prop(prnt(_.dropShadow.angle, 0.0), updatesRendering = true)
    val blur: Var[Double] = prop(prnt(_.dropShadow.blur, 0.0), updatesRendering = true)
    val color: Var[Color] = prop(prnt(_.dropShadow.color, Color.Black), updatesRendering = true)
    val distance: Var[Double] = prop(prnt(_.dropShadow.distance, 0.0), updatesRendering = true)
  }
  val fill: Var[Paint] = prop(prnt(_.fill, Paint.none), updatesRendering = true)
  object font {
    val family: Var[String] = prop(prnt(_.font.family, "Arial"), updatesRendering = true)
    val size: Var[Double] = prop(prnt(_.font.size, 26.0), updatesRendering = true)
    val style: Var[String] = prop(prnt(_.font.style, "normal"), updatesRendering = true)
    val variant: Var[String] = prop(prnt(_.font.variant, "normal"), updatesRendering = true)
    val weight: Var[String] = prop(prnt(_.font.weight, "normal"), updatesRendering = true)
  }
  val letterSpacing: Var[Double] = prop(prnt(_.letterSpacing, 0.0), updatesRendering = true)
  val lineHeight: Var[Double] = prop(prnt(_.lineHeight, 0.0), updatesRendering = true)
  val lineJoin: Var[String] = prop(prnt(_.lineJoin, "miter"), updatesRendering = true)
  val miterLimit: Var[Double] = prop(prnt(_.miterLimit, 10.0), updatesRendering = true)
  val padding: Var[Double] = prop(prnt(_.padding, 0.0), updatesRendering = true)
  val stroke: Var[Option[Stroke]] = prop(prnt(_.stroke, None), updatesRendering = true)
  val textBaseline: Var[String] = prop(prnt(_.textBaseline, "alphabetic"), updatesRendering = true)
  val wordWrap: Var[Boolean] = prop(prnt(_.wordWrap, false), updatesRendering = true)
}