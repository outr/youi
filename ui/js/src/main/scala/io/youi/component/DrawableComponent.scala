package io.youi.component
import io.youi.Compass
import io.youi.drawable.{Context, Drawable}
import io.youi.path.Path
import io.youi.theme.{DrawableComponentTheme, Theme}
import reactify.{Val, Var}

class DrawableComponent extends CanvasComponent with DrawableComponentTheme {
  protected val drawable: Var[Drawable] = Var(Drawable.None)
  val modified: Val[Long] = Val(drawable.modified)

  modified.attach(_ => invalidateRendering())

  override protected def defaultParentTheme: Theme = DrawableComponent

  override def componentType: String = "DrawableComponent"

  override protected def draw(context: Context): Unit = {
    context.save()
    preDraw(context)
    drawable().draw(context, 0.0, 0.0)
    postDraw(context)
    context.restore()
    borderDraw(context)
  }

  protected def preDraw(context: Context): Unit = {
    context.save()
    context.translate(border.size(Compass.West) - 1.0, border.size(Compass.North) - 1.0)
    border.background(size.width - border.width, size.height - border.height, context, background)
    context.restore()
  }

  protected def postDraw(context: Context): Unit = {}

  protected def borderDraw(context: Context): Unit = {
    context.save()
    border.draw(size.width, size.height, context)
    context.restore()
  }
}

object DrawableComponent extends DrawableComponentTheme {
  override protected def defaultParentTheme: Theme = CanvasComponent
}