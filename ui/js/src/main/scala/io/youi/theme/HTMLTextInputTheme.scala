package io.youi.theme

import io.youi.theme.mixins.HTMLFontTheme

trait HTMLTextInputTheme extends HTMLComponentTheme with HTMLFontTheme {
  lazy val value: StyleProp[String] = style[String]("value", "", StyleConnect.field[String], updatesTransform = false)
  lazy val placeholder: StyleProp[String] = style[String]("placeholder", "", StyleConnect.field[String], updatesTransform = false)
}