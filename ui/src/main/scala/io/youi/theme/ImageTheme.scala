package io.youi.theme

import io.youi.component.Component

trait ImageTheme extends ComponentTheme {
  override def defaultParent: Option[Theme] = Some(Component)

  private def prnt[T](f: ImageTheme => T, default: => T): T = parent.collect {
    case p: ImageTheme => p
  }.map(f).getOrElse(default)
}