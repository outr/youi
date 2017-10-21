package io.youi.component

import io.youi.drawable.{Context, Drawable}
import io.youi.theme.DrawableComponentTheme
import reactify.Var

class DrawableComponent extends Component with DrawableComponentTheme {
  override lazy val theme: Var[_ <: DrawableComponentTheme] = Var(DrawableComponent)

  override protected def defaultThemeParent = Some(theme)

  lazy val drawable: Var[Drawable]= Var(Drawable.None)

  override def `type`: String = "DrawableComponent"

  override protected def drawInternal(context: Context): Unit = drawable().draw(context, 0.0, 0.0)

  init()
}

object DrawableComponent extends DrawableComponentTheme