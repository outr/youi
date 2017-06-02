package io.youi.theme

import io.youi.component.Component

trait ContainerTheme extends ComponentTheme {
  override def defaultParent: Option[Theme] = Some(Component)

  private def prnt[T](f: ContainerTheme => T, default: => T): T = parent.collect {
    case p: ContainerTheme => p
  }.map(f).getOrElse(default)
}
