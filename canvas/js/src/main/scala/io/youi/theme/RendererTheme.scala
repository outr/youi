package io.youi.theme

import io.youi.component.Component

trait RendererTheme extends ContainerTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)

  private def prnt[T](f: RendererTheme => T, default: => T): T = parentTheme().collect {
    case p: RendererTheme => p
  }.map(f).getOrElse(default)
}