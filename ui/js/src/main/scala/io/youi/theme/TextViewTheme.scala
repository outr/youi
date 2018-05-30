package io.youi.theme

import io.youi.theme.mixins.FontTheme
import reactify.Var

trait TextViewTheme extends HTMLComponentTheme with FontTheme {
  lazy val value: StyleProp[String] = style[String]("value", "", StyleConnect.content[String], updatesTransform = true)
}