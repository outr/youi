package io.youi.component

import io.youi.Context
import io.youi.draw.Drawable
import io.youi.theme.DrawableComponentTheme
import reactify.Var

class DrawableComponent extends Component with DrawableComponentTheme {
  override lazy val theme: Var[_ <: DrawableComponentTheme] = Var(DrawableComponent)
  val drawable: Var[Drawable] = prop(Drawable.empty, updatesRendering = true)

  size.measured.width := drawable.boundingBox.x2
  size.measured.height := drawable.boundingBox.y2

  override protected def defaultThemeParent = Some(theme)

  override def draw(context: Context): Unit = {
    super.draw(context)

    drawable().draw(this, context)
  }
}

object DrawableComponent extends DrawableComponentTheme