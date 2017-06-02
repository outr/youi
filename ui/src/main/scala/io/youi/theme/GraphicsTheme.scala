package io.youi.theme

import io.youi.component.Component

trait GraphicsTheme extends ComponentTheme {
  override def defaultParent: Option[Theme] = Some(Component)
}