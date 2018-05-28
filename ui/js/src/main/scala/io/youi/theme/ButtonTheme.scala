package io.youi.theme

import reactify.Var

trait ButtonTheme extends Theme {
  lazy val value: Var[String] = style[String]("value", "", StyleConnect.field[String])
}