package io.youi.theme

trait VideoTheme extends ComponentTheme {
  override protected def defaultThemeParent: Option[Theme] = Some(Component)

  private def prnt[T](f: VideoTheme => T, default: => T): T = parentTheme().collect {
    case p: VideoTheme => p
  }.map(f).getOrElse(default)

  val autoPlay: Var[Boolean] = prop(prnt(_.autoPlay, false), updatesRendering = true)
  val loop: Var[Boolean] = prop(prnt(_.loop, false), updatesRendering = true)
  val muted: Var[Boolean] = prop(prnt(_.muted, false))
}