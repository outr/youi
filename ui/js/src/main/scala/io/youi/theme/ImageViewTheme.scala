package io.youi.theme

import io.youi.component.Component

trait ImageViewTheme extends ComponentTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)

  private def prnt[T](f: ComponentTheme => T, default: => T): T = parentTheme().collect {
    case p: ComponentTheme => p
  }.map(f).getOrElse(default)
}