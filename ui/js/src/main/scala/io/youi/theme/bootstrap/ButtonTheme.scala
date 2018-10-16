package io.youi.theme.bootstrap

import io.youi.component.bootstrap.{ButtonSize, ButtonType}
import io.youi.theme.{StyleConnect, StyleProp}
import io.youi.theme.mixins.HTMLFontTheme

trait ButtonTheme extends BootstrapTheme with HTMLFontTheme {
  val `type`: StyleProp[ButtonType] = style[ButtonType]("type", ButtonType.Primary, StyleConnect.classify[ButtonType])
  val buttonSize: StyleProp[ButtonSize] = style[ButtonSize]("buttonSize", ButtonSize.Normal, StyleConnect.classify[ButtonSize])
  val block: StyleProp[Boolean] = style[Boolean]("block", false, StyleConnect.flag(on = Some("btn-block")))
}