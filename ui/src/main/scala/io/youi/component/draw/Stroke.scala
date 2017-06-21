package io.youi.component.draw

import io.youi.component.Component
import io.youi.style.Paint
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.scalajs.js

case class Stroke(paint: Paint,
                  lineWidth: Double,
                  lineDash: List[Double],
                  lineDashOffset: Double,
                  lineCap: LineCap,
                  lineJoin: LineJoin) extends Drawable {
  def set(component: Component, context: CanvasRenderingContext2D): Unit = {
    if (paint.nonEmpty) {
      context.strokeStyle = paint(component).asInstanceOf[js.Any]
      context.lineWidth = lineWidth
      context.setLineDash(js.Array(lineDash: _*))
      context.lineDashOffset = lineDashOffset
      context.lineCap = lineCap.value
      context.lineJoin = lineJoin.value
    }
  }
  def stroke(context: CanvasRenderingContext2D): Unit = {
    if (paint.nonEmpty) {
      context.stroke()
    }
  }

  override def boundingBox: BoundingBox = BoundingBox.zero

  override def draw(component: Component, context: CanvasRenderingContext2D): Unit = {
    set(component, context)
    stroke(context)
  }
}

sealed abstract class LineCap(val value: String)

object LineCap {
  case object Butt extends LineCap("butt")
  case object Round extends LineCap("round")
  case object Square extends LineCap("square")
}

sealed abstract class LineJoin(val value: String)

object LineJoin {
  case object Round extends LineJoin("round")
  case object Bevel extends LineJoin("bevel")
  case object Miter extends LineJoin("miter")
}