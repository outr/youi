package io.youi.component

import io.youi.drawable.{Context, Drawable}
import io.youi.paint.Border
import io.youi.{Compass, dom}
import org.scalajs.dom.html
import reactify.{Val, Var}

class DrawableView(canvas: html.Canvas = dom.create.canvas) extends CanvasView(canvas) {
  protected val drawable: Var[Drawable] = Var(Drawable.None)
  val modified: Val[Long] = Val(drawable.modified)

  val border: Var[Border] = Var(Border.empty)

  drawable.on(render())
  modified.on(render())

  override protected def draw(content: Context): Unit = {
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
    border.background(width - border.width, height - border.height, context, background)
    context.restore()
  }

  protected def postDraw(context: Context): Unit = {}

  protected def borderDraw(context: Context): Unit = {
    context.save()
    border.draw(width, height, context)
    context.restore()
  }
}