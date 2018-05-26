package io.youi.theme.mixins

import io.youi.Color
import io.youi.style.{FontFamily, FontWeight}
import io.youi.theme.{StyleConnect, Theme}
import reactify.Var

trait FontTheme extends Theme {
  object font {
    val family: Var[FontFamily] = style[FontFamily]("font-family", FontFamily.default, StyleConnect.style[FontFamily])
    val weight: Var[FontWeight] = style[FontWeight]("font-weight", FontWeight.default, StyleConnect.style[FontWeight])
    val size: Var[Double] = style[Double]("font-size", 0.0, StyleConnect.style[Double])
  }

  val color: Var[Color] = style[Color]("color", Color.Black, StyleConnect.style[Color])
}
