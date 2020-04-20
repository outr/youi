package io.youi.paint

case class RadialGradientPaint(x0: Double,
                               y0: Double,
                               r0: Double,
                               x1: Double,
                               y1: Double,
                               r1: Double,
                               stops: Vector[GradientStop] = Vector.empty) extends GradientPaint {
  override def replaceStops(stops: Vector[GradientStop]): RadialGradientPaint = copy(stops = stops)
  override def asJS(context: Context): js.Any = {
    val ctx = context.ctx
    val g = ctx.createRadialGradient(x0, y0, r0, x1, y1, r1)
    stops.foreach { stop =>
      g.addColorStop(stop.offset, stop.color.toRGBA)
    }
    g
  }

  override def asCSS(): String = ???
}
