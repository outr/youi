package io.youi.theme

import io.youi.component.Component

trait AbstractContainerTheme extends ComponentTheme {
  override def defaultParent: Option[Theme] = Some(Component)

  private def prnt[T](f: AbstractContainerTheme => T, default: => T): T = parent().collect {
    case p: AbstractContainerTheme => p
  }.map(f).getOrElse(default)
}
