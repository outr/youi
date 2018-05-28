package io.youi.theme.bootstrap

import io.youi.component.bootstrap.{ButtonSize, ButtonType}
import io.youi.theme.mixins.FontTheme
import reactify.Var

trait ButtonTheme extends BootstrapTheme with FontTheme {
  val `type`: Var[ButtonType] = classify(Var(ButtonType.Primary), ButtonType)
  val buttonSize: Var[ButtonSize] = classify(Var(ButtonSize.Normal), ButtonSize)
  val block: Var[Boolean] = classifyFlag(Var(false), on = Some("btn-block"))
}
