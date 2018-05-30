package io.youi.theme.mixins

import io.youi.Color
import io.youi.style.{FontFamily, FontWeight, WhiteSpace}
import io.youi.theme.{StyleConnect, StyleProp, Theme}

trait FontTheme extends Theme {
  object font {
    val family: StyleProp[FontFamily] = style[FontFamily]("font-family", FontFamily.default, StyleConnect.style[FontFamily], updatesTransform = true)
    val weight: StyleProp[FontWeight] = style[FontWeight]("font-weight", FontWeight.default, StyleConnect.style[FontWeight], updatesTransform = true)
    val size: StyleProp[Double] = style[Double]("font-size", 0.0, StyleConnect.style[Double], updatesTransform = true)
  }
  val whiteSpace: StyleProp[WhiteSpace] = style[WhiteSpace]("white-space", WhiteSpace.Normal, StyleConnect.style[WhiteSpace], updatesTransform = true)

  val color: StyleProp[Color] = style[Color]("color", Color.Black, StyleConnect.style[Color])
}

