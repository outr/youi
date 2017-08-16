package io.youi.theme

import io.youi.component.Component

trait ImageViewTheme extends ComponentTheme {
  override def defaultParent: Option[Theme] = Some(Component)

  private def prnt[T](f: ImageViewTheme => T, default: => T): T = parent().collect {
    case p: ImageViewTheme => p
  }.map(f).getOrElse(default)
}