package io.youi.theme.mixins

import io.youi.Color
import io.youi.style.{FontFamily, FontWeight, WhiteSpace}
import io.youi.theme.{StyleConnect, Theme}
import reactify.Var

trait FontTheme extends Theme {
  object font {
    val family: Var[FontFamily] = style[FontFamily]("font-family", FontFamily.default, StyleConnect.style[FontFamily], updatesTransform = true)
    val weight: Var[FontWeight] = style[FontWeight]("font-weight", FontWeight.default, StyleConnect.style[FontWeight], updatesTransform = true)
    val size: Var[Double] = style[Double]("font-size", 0.0, StyleConnect.style[Double], updatesTransform = true)
  }
  val whiteSpace: Var[WhiteSpace] = style[WhiteSpace]("white-space", WhiteSpace.Normal, StyleConnect.style[WhiteSpace], updatesTransform = true)

  val color: Var[Color] = style[Color]("color", Color.Black, StyleConnect.style[Color])
}

