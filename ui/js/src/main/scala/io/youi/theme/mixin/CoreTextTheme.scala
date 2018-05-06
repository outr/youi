package io.youi.theme.mixin

import io.youi.Color
import io.youi.style.{FontFamily, FontWeight}
import io.youi.theme.ComponentTheme
import reactify.Var

trait CoreTextTheme extends ComponentTheme {
  private def prnt[T](f: CoreTextTheme => T, default: => T): T = parentTheme().collect {
    case p: CoreTextTheme => p
  }.map(f).getOrElse(default)

  object font {
    val family: Var[FontFamily] = Var(prnt(_.font.family, FontFamily.default))
    val weight: Var[FontWeight] = Var(prnt(_.font.weight, FontWeight.default))
    val size: Var[Double] = Var(prnt(_.font.size, 12.0))
  }

  val color: Var[Color] = Var(prnt(_.color, Color.Black))
}
