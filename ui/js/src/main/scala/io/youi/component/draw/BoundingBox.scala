package io.youi.component.draw

import io.youi.Size
import io.youi.component.draw.path._

case class BoundingBox(x1: Double, y1: Double, x2: Double, y2: Double) {
  def adjustX: Double = -x1
  def adjustY: Double = height - y2
  def centerX: Double = x1 + (width / 2.0)
  def centerY: Double = y1 + (height / 2.0)
  def width: Double = x2 - x1
  def height: Double = y2 - y1

  def merge(that: BoundingBox): BoundingBox = BoundingBox(
    x1 = math.min(this.x1, that.x1),
    y1 = math.min(this.y1, that.y1),
    x2 = math.max(this.x2, that.x2),
    y2 = math.max(this.y2, that.y2)
  )

  def touching(x: Double, y: Double): Option[TouchData] = {
    if (x >= x1 && x <= x2 && y >= 0 && y <= height) {
      val deltaX = x - centerX
      val deltaY = y - centerY
      Some(TouchData(deltaX, deltaY, BoundingBox.distance(x, centerX, y, centerY)))
    } else {
      None
    }
  }

  def shift(offsetX: Double, offsetY: Double): BoundingBox = if (offsetX == 0.0 && offsetY == 0.0) {
    this
  } else {
    BoundingBox(
      x1 = x1 + offsetX,
      y1 = y1 + offsetY,
      x2 = x2 + offsetX,
      y2 = y2 + offsetY
    )
  }

  def toSize: Size = Size(width, height)

  override def toString: String = s"BoundingBox(x1: $x1, y1: $y1, x2: $x2, y2: $y2, adjustX: $adjustX, adjustY: $adjustY, width: $width, height: $height)"
}

object BoundingBox {
  val zero: BoundingBox = BoundingBox(0.0, 0.0, 0.0, 0.0)

  def apply(pathActions: List[PathAction]): BoundingBox = {
    var minX = Double.MaxValue
    var minY = Double.MaxValue
    var maxX = Double.MinValue
    var maxY = Double.MinValue

    var cx = 0.0
    var cy = 0.0

    def adjustTo(newX: Double, newY: Double, oldX: Double = cx, oldY: Double = cy, updateCoordinates: Boolean = true): Unit = {
      minX = math.min(oldX, math.min(minX, newX))
      minY = math.min(oldY, math.min(minY, newY))
      maxX = math.max(oldX, math.max(maxX, newX))
      maxY = math.max(oldY, math.max(maxY, newY))

      cx = newX
      cy = newY
    }

    pathActions.foreach {
      case BeginPath => // Nothing
      case ClosePath => // Nothing
      case CurveTo(_, _, _, _, x, y) => adjustTo(x, y)
      case LineTo(x, y) => adjustTo(x, y)
      case MoveTo(x, y) => {
        cx = x
        cy = y
      }
      case QuadraticCurveTo(_, _, x, y) => adjustTo(x, y)
      case Rectangle(x, y, width, height) => adjustTo(x + width, y + height, updateCoordinates = false)
      case RoundedRectangle(x, y, width, height, _) => adjustTo(x + width, y + height, updateCoordinates = false)
    }
    BoundingBox(minX, minY, maxX, maxY)
  }

  def distance(x1: Double, y1: Double, x2: Double, y2: Double): Double = {
    val xd = x1 - x2
    val yd = y1 - y2
    math.sqrt((xd * xd) + (yd * yd))
  }
}