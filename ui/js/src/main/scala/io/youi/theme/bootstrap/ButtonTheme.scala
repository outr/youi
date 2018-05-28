package io.youi.theme.bootstrap

import io.youi.component.bootstrap.{ButtonSize, ButtonType}
import io.youi.theme.StyleConnect
import io.youi.theme.mixins.FontTheme
import reactify.Var

trait ButtonTheme extends BootstrapTheme with FontTheme {
  val `type`: Var[ButtonType] = style[ButtonType]("type", ButtonType.Primary, StyleConnect.classify[ButtonType])
  val buttonSize: Var[ButtonSize] = style[ButtonSize]("buttonSize", ButtonSize.Normal, StyleConnect.classify[ButtonSize])
  val block: Var[Boolean] = style[Boolean]("block", false, StyleConnect.flag(on = Some("btn-block")))
}
