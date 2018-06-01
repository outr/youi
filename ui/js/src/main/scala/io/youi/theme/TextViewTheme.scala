package io.youi.theme

trait TextViewTheme extends ComponentTheme {
  lazy val value: StyleProp[String] = style[String]("value", "", StyleConnect.content[String], updatesTransform = true)
}