package io.youi.theme

trait HTMLTextAreaTheme extends HTMLComponentTheme with HTMLFontTheme {
  lazy val value: StyleProp[String] = style[String]("value", "", StyleConnect.field[String], updatesTransform = false)
}
