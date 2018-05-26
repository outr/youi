package io.youi.theme

import io.youi.theme.mixins.FontTheme
import reactify.Var

trait TextViewTheme extends HTMLComponentTheme with FontTheme {
  lazy val value: Var[String] = style[String]("value", "", StyleConnect.html[String], updatesTransform = true)
}