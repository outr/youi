package io.youi.font

import io.youi.{BoundingBox, Context}
import io.youi.component.Component
import io.youi.draw.Drawable
import io.youi.event.TouchData
import org.scalajs.dom.raw.CanvasRenderingContext2D

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class TextPaths(paths: Vector[TextPath]) extends Drawable {
  lazy val boundingBox: BoundingBox = if (paths.nonEmpty) {
    var bb = paths.head.path.boundingBox
    paths.tail.foreach(other => bb = bb.merge(other.path.boundingBox))
    bb
  } else {
    BoundingBox.zero
  }

  def zero(): TextPaths = TextPaths(paths.zipWithIndex.map {
    case (tp, index) => TextPath(tp.char, index, tp.path.shift(boundingBox.adjustX, boundingBox.adjustY))
  })

  private def touchingPath(x: Double, y: Double, x1: Double, y1: Double, x2: Double, y2: Double): Option[TouchData] = {
    val centerX = x1 + ((x2 - x1) / 2.0)
    val centerY = y1 + ((y2 - y1) / 2.0)
    if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
      val deltaX = x - centerX
      val deltaY = y - centerY
      Some(TouchData(deltaX, deltaY, BoundingBox.distance(x, centerX, y, centerY)))
    } else {
      None
    }
  }

  def touching(x: Double, y: Double): Vector[Touching] = {
    val ry = boundingBox.height - y
    paths.flatMap { tp =>
      val bb = tp.path.boundingBox
      touchingPath(x, ry, bb.x1, 0.0, bb.x2, boundingBox.height).map(d => Touching(tp, d))
    }.sortBy(_.data.distance)
  }

  def draw(component: Component, context: Context): Unit = {
    context.translate(boundingBox.adjustX, boundingBox.adjustY)
    paths.foreach(_.path.draw(component, context))
  }
}
