package io.youi.theme

import io.youi.component.Component
import reactify.Var

trait VideoTheme extends ComponentTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)

  private def prnt[T](f: VideoTheme => T, default: => T): T = parentTheme().collect {
    case p: VideoTheme => p
  }.map(f).getOrElse(default)

  val autoPlay: Var[Boolean] = prop(prnt(_.autoPlay, false), updatesRendering = true)
}
