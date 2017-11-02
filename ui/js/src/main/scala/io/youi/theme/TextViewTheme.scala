package io.youi.theme

import io.youi.Color
import io.youi.component.Component
import io.youi.font.Font
import io.youi.paint.{Paint, Stroke}
import reactify.Var

trait TextViewTheme extends ComponentTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)

  private def prnt[T](f: TextViewTheme => T, default: => T): T = parentTheme().collect {
    case p: TextViewTheme => p
  }.map(f).getOrElse(default)

  object shadow {
    val enabled: Var[Boolean] = prop(prnt(_.shadow.enabled, false), updatesRendering = true)
    val blur: Var[Double] = prop(prnt(_.shadow.blur, 0.0), updatesRendering = true)
    val color: Var[Color] = prop(prnt(_.shadow.color, Color.Black), updatesRendering = true)
    val x: Var[Double] = prop(prnt(_.shadow.x, 2.0), updatesRendering = true)
    val y: Var[Double] = prop(prnt(_.shadow.y, 2.0), updatesRendering = true)
  }
  val fill: Var[Paint] = prop(prnt(_.fill, Paint.none), updatesRendering = true)
  object font {
    val file: Var[Font] = Var(prnt(_.font.file, Font.empty))
    val size: Var[Double] = Var(prnt(_.font.size, 26.0))
    val kerning: Var[Boolean] = Var(prnt(_.font.kerning, true))
  }
  val lineJoin: Var[String] = prop(prnt(_.lineJoin, "miter"), updatesRendering = true)
  val miterLimit: Var[Double] = prop(prnt(_.miterLimit, 10.0), updatesRendering = true)
  val stroke: Var[Stroke] = prop(prnt(_.stroke, Stroke.none), updatesRendering = true)
  val textBaseline: Var[String] = prop(prnt(_.textBaseline, "alphabetic"), updatesRendering = true)
  object selection {
    val enabled: Var[Boolean] = Var(prnt(_.selection.enabled, true))
    val fill: Var[Paint] = Var(prnt(_.selection.fill, Color.LightBlue))
    val stroke: Var[Paint] = Var(prnt(_.selection.stroke, Paint.none))
  }

  // Default TextView to be cached for better performance
  cache := prnt(_.cache, true)
}