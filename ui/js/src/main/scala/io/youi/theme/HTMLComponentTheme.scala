package io.youi.theme

import io.youi.component.Component

trait HTMLComponentTheme extends ComponentTheme {
  override def defaultParent: Option[Theme] = Some(Component)
}