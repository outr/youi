package io.youi.theme

import io.youi.theme.mixins.HTMLFontTheme

trait HTMLTextAreaTheme extends HTMLComponentTheme with HTMLFontTheme {
  lazy val value: StyleProp[String] = style[String]("value", "", StyleConnect.field[String], updatesTransform = false)
}
