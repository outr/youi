package io.youi.theme.mixins

import io.youi.Color
import io.youi.font.{GoogleFont, GoogleFontWeight}
import io.youi.style.{FontFamily, FontWeight, TextAlign, WhiteSpace}
import io.youi.theme.{StyleConnect, StyleProp, Theme}

trait HTMLFontTheme extends Theme {
  object font {
    def :=(gf: GoogleFont): Unit = {
      family := FontFamily(gf.family)
      weight := FontWeight.default
    }
    def :=(gfw: GoogleFontWeight): Unit = {
      family := FontFamily(gfw.font.family)
      weight := FontWeight(gfw.name)
    }
    def @=(gf: GoogleFont): Unit = :=(gf)
    def @=(gfw: GoogleFontWeight): Unit = :=(gfw)

    val family: StyleProp[FontFamily] = style[FontFamily]("font-family", FontFamily.default, StyleConnect.style[FontFamily], updatesTransform = true)
    val weight: StyleProp[FontWeight] = style[FontWeight]("font-weight", FontWeight.default, StyleConnect.style[FontWeight], updatesTransform = true)
    val size: StyleProp[Double] = style[Double]("font-size", 12.0, StyleConnect.style[Double], updatesTransform = true)
  }
  val textAlign: StyleProp[TextAlign] = style[TextAlign]("text-align", TextAlign.Initial, StyleConnect.style[TextAlign])
  val whiteSpace: StyleProp[WhiteSpace] = style[WhiteSpace]("white-space", WhiteSpace.Normal, StyleConnect.style[WhiteSpace], updatesTransform = true)

  val color: StyleProp[Color] = style[Color]("color", Color.Black, StyleConnect.style[Color])
}