package io.youi.theme

trait ButtonTheme extends Theme {
  lazy val value: StyleProp[String] = style[String]("value", "", StyleConnect.field[String])
}