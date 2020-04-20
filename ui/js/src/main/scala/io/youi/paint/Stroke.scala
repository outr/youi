package io.youi.paint

case class Stroke(paint: Paint,
                  path: Option[Path] = None,
                  lineWidth: Double = 1.0,
                  lineDash: List[Double] = Nil,
                  lineDashOffset: Double = 0.0,
                  lineCap: LineCap = LineCap.Butt,
                  lineJoin: LineJoin = LineJoin.Miter) extends PathAction {
  def isEmpty: Boolean = paint.isEmpty
  def nonEmpty: Boolean = !isEmpty

  override def draw(context: Context, x: Double, y: Double, scaleX: Double, scaleY: Double): Unit = if (nonEmpty) {
    path match {
      case Some(p) => {
        context.stroke(this, apply = false)
        context.ctx.asInstanceOf[js.Dynamic].stroke(p.path2d)
      }
      case None => context.stroke(this, apply = true)
    }
  }
}

object Stroke {
  lazy val none: Stroke = Stroke(Paint.none, None, 0.0)

  def draw(context: Context,
           paint: Paint,
           path: Option[Path] = None,
           lineWidth: Double = 1.0,
           lineDash: List[Double] = Nil,
           lineDashOffset: Double = 0.0,
           lineCap: LineCap = LineCap.Butt,
           lineJoin: LineJoin = LineJoin.Miter): Unit = if (paint.nonEmpty) {
    path match {
      case Some(p) => {
        context.stroke(
          paint = paint,
          lineWidth = lineWidth,
          lineDash = lineDash,
          lineDashOffset = lineDashOffset,
          lineCap = lineCap,
          lineJoin = lineJoin,
          apply = false
        )
        context.ctx.asInstanceOf[js.Dynamic].stroke(p.path2d)
      }
      case None => context.stroke(
        paint = paint,
        lineWidth = lineWidth,
        lineDash = lineDash,
        lineDashOffset = lineDashOffset,
        lineCap = lineCap,
        lineJoin = lineJoin,
        apply = true
      )
    }
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