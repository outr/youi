package io.youi.component
import io.youi.drawable.{Context, Drawable}
import io.youi.theme.{DrawableComponentTheme, Theme}
import reactify.{Val, Var}

class DrawableComponent extends CanvasComponent {
  protected val drawable: Var[Drawable] = Var(Drawable.None)
  val modified: Val[Long] = Val(drawable.modified)

  modified.attach(_ => updateRendering())

  override protected def defaultParentTheme: Theme = DrawableComponent

  override def componentType: String = "DrawableComponent"

  override protected def draw(context: Context): Unit = {
    drawable().draw(context, 0.0, 0.0)
  }
}

object DrawableComponent extends DrawableComponentTheme {
  override protected def defaultParentTheme: Theme = CanvasComponent
}