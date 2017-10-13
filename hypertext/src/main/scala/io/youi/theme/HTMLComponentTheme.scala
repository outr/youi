package io.youi.theme

import io.youi.component.Component

trait HTMLComponentTheme extends ComponentTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)
}