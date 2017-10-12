package io.youi.theme

import io.youi.component.Component

trait ContainerTheme extends AbstractContainerTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)

  private def prnt[T](f: ContainerTheme => T, default: => T): T = parentTheme().collect {
    case p: ContainerTheme => p
  }.map(f).getOrElse(default)
}
