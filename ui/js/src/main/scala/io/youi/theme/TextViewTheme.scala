package io.youi.theme

import io.youi.component.Component
import io.youi.style.{FontFamily, FontWeight}
import reactify.Var

trait TextViewTheme extends ComponentTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)

  private def prnt[T](f: TextViewTheme => T, default: => T): T = parentTheme().collect {
    case p: TextViewTheme => p
  }.map(f).getOrElse(default)

  object font {
    val family: Var[FontFamily] = Var(prnt(_.font.family, FontFamily.default))
    val weight: Var[FontWeight] = Var(prnt(_.font.weight, FontWeight.default))
    val size: Var[Double] = Var(prnt(_.font.size, 12.0))
  }

  /*object shadow {
    val enabled: Var[Boolean] = prop(prnt(_.shadow.enabled, false), updatesRendering = true)
    val blur: Var[Double] = prop(prnt(_.shadow.blur, 0.0), updatesRendering = true)
    val color: Var[Color] = prop(prnt(_.shadow.color, Color.Black), updatesRendering = true)
    val x: Var[Double] = prop(prnt(_.shadow.x, 2.0), updatesRendering = true)
    val y: Var[Double] = prop(prnt(_.shadow.y, 2.0), updatesRendering = true)
  }
  lazy val fill: Var[Paint] = prop(prnt(_.fill, Paint.none), updatesRendering = true)
  object font {
    val file: Var[Font] = Var(prnt(_.font.file, Font.empty))
    val size: Var[Double] = Var(prnt(_.font.size, 26.0))
    val kerning: Var[Boolean] = Var(prnt(_.font.kerning, true))
  }
  lazy val lineJoin: Var[String] = prop(prnt(_.lineJoin, "miter"), updatesRendering = true)
  lazy val miterLimit: Var[Double] = prop(prnt(_.miterLimit, 10.0), updatesRendering = true)
  lazy val stroke: Var[Stroke] = prop(prnt(_.stroke, Stroke.none), updatesRendering = true)
  lazy val textBaseline: Var[String] = prop(prnt(_.textBaseline, "alphabetic"), updatesRendering = true)
  object selection {
    val enabled: Var[Boolean] = Var(prnt(_.selection.enabled, true))
    val fill: Var[Paint] = Var(prnt(_.selection.fill, Color.LightBlue))
    val stroke: Var[Paint] = Var(prnt(_.selection.stroke, Paint.none))
  }

  // Default TextView to be cached for better performance
  cache := prnt(_.cache, true)*/
}