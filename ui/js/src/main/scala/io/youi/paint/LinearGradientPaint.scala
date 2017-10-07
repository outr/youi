package io.youi.paint

import io.youi.drawable.Context

import scala.scalajs.js

case class LinearGradientPaint(x0: Double,
                               y0: Double,
                               x1: Double,
                               y1: Double,
                               stops: Vector[GradientStop] = Vector.empty) extends GradientPaint {
  override def replaceStops(stops: Vector[GradientStop]): LinearGradientPaint = copy(stops = stops)
  override def asJS(context: Context): js.Any = {
    val ctx = context.ctx
    val g = ctx.createLinearGradient(x0, y0, x1, y1)
    stops.foreach { stop =>
      g.addColorStop(stop.offset, stop.color.toRGBA)
    }
    g
  }
}
