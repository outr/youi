package io.youi.theme.bootstrap

import io.youi.component.Component
import io.youi.theme.{ComponentTheme, Theme}
import io.youi.theme.mixin.CoreTextTheme

trait ButtonTheme extends ComponentTheme with CoreTextTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)
}