package io.youi.component

import io.youi.drawable.{Context, Drawable}
import io.youi.theme.ComponentTheme
import reactify.Var

class DrawableComponent extends Component with ComponentTheme {
  override lazy val theme: Var[ComponentTheme] = Var(DrawableComponent)

  lazy val drawable: Var[Drawable]= Var(Drawable.None)

  override def `type`: String = "DrawableComponent"

  override protected def drawInternal(context: Context): Unit = drawable().draw(context, 0.0, 0.0)
}

object DrawableComponent extends ComponentTheme