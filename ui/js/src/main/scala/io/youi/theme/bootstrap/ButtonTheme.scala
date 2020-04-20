package io.youi.theme.bootstrap

trait ButtonTheme extends BootstrapTheme with HTMLFontTheme {
  val `type`: StyleProp[ButtonType] = style[ButtonType]("type", ButtonType.Primary, StyleConnect.classify[ButtonType])
  val buttonSize: StyleProp[ButtonSize] = style[ButtonSize]("buttonSize", ButtonSize.Normal, StyleConnect.classify[ButtonSize])
  val block: StyleProp[Boolean] = style[Boolean]("block", false, StyleConnect.flag(on = Some("btn-block")))
}