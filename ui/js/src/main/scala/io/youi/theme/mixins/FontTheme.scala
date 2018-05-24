package io.youi.theme.mixins

import io.youi.Color
import io.youi.style.{FontFamily, FontWeight, Length}
import io.youi.theme.{StyleConnect, Theme}
import reactify.Var

trait FontTheme extends Theme {
  object font {
    val family: Var[FontFamily] = style[FontFamily]("fontFamily", FontFamily.default, StyleConnect.style[FontFamily])
    val weight: Var[FontWeight] = style[FontWeight]("fontWeight", FontWeight.default, StyleConnect.style[FontWeight])
    val size: Var[Length] = style[Length]("fontSize", Length.default, StyleConnect.style[Length])
  }

  val color: Var[Color] = style[Color]("color", Color.Black, StyleConnect.style[Color])
}
