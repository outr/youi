package io.youi.theme

import io.youi.component.Component

trait AbstractContainerTheme extends ComponentTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)

  private def prnt[T](f: AbstractContainerTheme => T, default: => T): T = parentTheme().collect {
    case p: AbstractContainerTheme => p
  }.map(f).getOrElse(default)
}
