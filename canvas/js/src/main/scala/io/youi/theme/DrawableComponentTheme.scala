package io.youi.theme

import io.youi.component.Component

trait DrawableComponentTheme extends ComponentTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)

  private def prnt[T](f: DrawableComponentTheme => T, default: => T): T = parentTheme().collect {
    case p: DrawableComponentTheme => p
  }.map(f).getOrElse(default)
}