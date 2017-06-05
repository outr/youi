package io.youi.component
import io.youi.ItemContainer
import io.youi.component.draw.Drawable
import io.youi.style.Paint
import io.youi.theme.DrawableComponentTheme
import org.scalajs.dom.raw.CanvasRenderingContext2D
import reactify.Var

import scala.scalajs.js

class DrawableComponent extends CanvasComponent {
  override lazy val theme: Var[_ <: DrawableComponentTheme] = Var(DrawableComponent)

  val preDraw: ItemContainer[Drawable] = new ItemContainer[Drawable]()
  val postDraw: ItemContainer[Drawable] = new ItemContainer[Drawable]()
  val drawable: Var[Drawable] = Var(Drawable.empty)

  val fill: Var[Paint] = Var(theme.fill)
  val stroke: Var[Paint] = Var(theme.stroke)
  val lineWidth: Var[Double] = Var(theme.lineWidth)
  val lineDash: Var[List[Double]] = Var(theme.lineDash)
  val background: Var[Paint] = Var(theme.background)

  drawable.on(reDraw.flag())
  fill.on(reDraw.flag())
  stroke.on(reDraw.flag())

  size.measured.width := drawable().boundingBox.width
  size.measured.height := drawable().boundingBox.height

  override protected def draw(context: CanvasRenderingContext2D): Unit = {
    background() match {
      case paint if paint == Paint.none => // No background
      case paint => {
        context.fillStyle = paint(this).asInstanceOf[js.Any]
        context.fillRect(0.0, 0.0, size.width(), size.height())
      }
    }

    preDraw.foreach(_.draw(this, context))
    if (fill().nonEmpty) {
      context.fillStyle = fill().apply(this).asInstanceOf[js.Any]
    }
    if (stroke().nonEmpty) {
      context.strokeStyle = stroke().apply(this).asInstanceOf[js.Any]
      context.lineWidth = lineWidth()
      context.setLineDash(js.Array(lineDash(): _*))
    }
    drawable().draw(this, context)
    if (autoPaint) {
      if (fill().nonEmpty) {
        context.fill()
      }
      if (stroke().nonEmpty) {
        context.stroke()
      }
    }
    postDraw.foreach(_.draw(this, context))
  }

  protected def autoPaint: Boolean = true
}

object DrawableComponent extends DrawableComponentTheme