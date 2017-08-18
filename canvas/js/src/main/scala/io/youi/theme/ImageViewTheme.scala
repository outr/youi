package io.youi.theme

import io.youi.component.Component

trait ImageViewTheme extends ComponentTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)

  private def prnt[T](f: ImageViewTheme => T, default: => T): T = parentTheme().collect {
    case p: ImageViewTheme => p
  }.map(f).getOrElse(default)
}