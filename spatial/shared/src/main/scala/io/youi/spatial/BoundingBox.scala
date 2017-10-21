package io.youi.spatial

trait BoundingBox extends SpatialValue[BoundingBox] {
  def x1: Double
  def y1: Double
  def x2: Double
  def y2: Double

  def set(x1: Double = x1, y1: Double = y1, x2: Double = x2, y2: Double = y2): BoundingBox = duplicate(x1, y1, x2, y2)
  def duplicate(x1: Double = x1, y1: Double = y1, x2: Double = x2, y2: Double = y2): BoundingBox
  def set(that: BoundingBox): BoundingBox = set(that.x1, that.y1, that.x2, that.y2)
  def zero(): BoundingBox = set(0.0, 0.0, 0.0, 0.0)

  def adjustX: Double = -x1
  def adjustY: Double = height - y2
  def centerX: Double = x1 + (width / 2.0)
  def centerY: Double = y1 + (height / 2.0)
  def width: Double = x2 - x1
  def height: Double = y2 - y1

  def merge(that: BoundingBox): BoundingBox = duplicate(
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

  def intersects(that: BoundingBox): Boolean = !(x1 > that.x2 || x2 < that.x1 || y1 > that.y2 || y2 < that.y1)

  def shift(offsetX: Double, offsetY: Double): BoundingBox = if (offsetX == 0.0 && offsetY == 0.0) {
    this
  } else {
    duplicate(
      x1 = x1 + offsetX,
      y1 = y1 + offsetY,
      x2 = x2 + offsetX,
      y2 = y2 + offsetY
    )
  }

  def toSize: Size = Size(width, height)

  override def toString: String = s"BoundingBox(x1: $x1, y1: $y1, x2: $x2, y2: $y2, adjustX: $adjustX, adjustY: $adjustY, width: $width, height: $height)"
}

case class ImmutableBoundingBox(x1: Double, y1: Double, x2: Double, y2: Double) extends BoundingBox {
  override def zero(): BoundingBox = BoundingBox.zero
  override def duplicate(x1: Double = x1, y1: Double = y1, x2: Double = x2, y2: Double = y2): BoundingBox = {
    BoundingBox(x1, y1, x2, y2)
  }
  override def isMutable: Boolean = false
  override def mutable: MutableBoundingBox = new MutableBoundingBox(x1, y1, x2, y2)
  override def immutable: ImmutableBoundingBox = this
}

class MutableBoundingBox(var x1: Double, var y1: Double, var x2: Double, var y2: Double) extends BoundingBox {
  override def set(x1: Double = x1, y1: Double = y1, x2: Double = x2, y2: Double = y2): BoundingBox = {
    this.x1 = x1
    this.y1 = y1
    this.x2 = x2
    this.y2 = y2
    this
  }

  override def duplicate(x1: Double = x1, y1: Double = y1, x2: Double = x2, y2: Double = y2): BoundingBox = {
    BoundingBox.mutable(x1, y1, x2, y2)
  }
  override def isMutable: Boolean = true
  override def mutable: MutableBoundingBox = this
  override def immutable: ImmutableBoundingBox = BoundingBox(x1, y1, x2, y2)
}

object BoundingBox {
  val zero: BoundingBox = BoundingBox()
  val temp: MutableBoundingBox = mutable()

  def distance(x1: Double, y1: Double, x2: Double, y2: Double): Double = {
    val xd = x1 - x2
    val yd = y1 - y2
    math.sqrt((xd * xd) + (yd * yd))
  }

  def apply(x1: Double = 0.0, y1: Double = 0.0, x2: Double = 0.0, y2: Double = 0.0): ImmutableBoundingBox = {
    ImmutableBoundingBox(x1, y1, x2, y2)
  }

  def mutable(x1: Double = 0.0, y1: Double = 0.0, x2: Double = 0.0, y2: Double = 0.0): MutableBoundingBox = {
    new MutableBoundingBox(x1, y1, x2, y2)
  }
}

case class TouchData(deltaX: Double, deltaY: Double, distance: Double)