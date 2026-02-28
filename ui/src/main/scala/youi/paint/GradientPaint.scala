package youi.paint

import youi.Color

trait GradientPaint extends Paint {
  def stops: Vector[GradientStop]
  def replaceStops(stops: Vector[GradientStop]): GradientPaint

  def withStops(stops: GradientStop*): GradientPaint = replaceStops(this.stops ++ stops)
  def distributeColors(colors: Color*): GradientPaint = {
    val length = colors.length
    val adjust = 1.0 / (length.toDouble - 1)
    var offset = 0.0
    val stops = colors.map { color =>
      val stop = GradientStop(color, offset)
      offset += adjust
      stop
    }.toVector
    replaceStops(stops)
  }
}
