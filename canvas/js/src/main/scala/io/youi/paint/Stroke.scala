package io.youi.paint

case class Stroke(paint: Paint,
                  lineWidth: Double = 1.0,
                  lineDash: List[Double] = Nil,
                  lineDashOffset: Double = 0.0,
                  lineCap: LineCap = LineCap.Butt,
                  lineJoin: LineJoin = LineJoin.Miter) {
  def isEmpty: Boolean = paint.isEmpty
  def nonEmpty: Boolean = !isEmpty
}

object Stroke {
  lazy val none: Stroke = Stroke(Paint.none)
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