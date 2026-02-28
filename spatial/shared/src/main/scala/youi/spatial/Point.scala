package youi.spatial

sealed trait Point extends SpatialValue[Point] {
  def x: Double
  def y: Double

  def set(x: Double, y: Double): Point
  def set(that: Point): Point = set(that.x, that.y)
  def duplicate(): Point
  override def equals(obj: scala.Any): Boolean = obj match {
    case that: Point => x <=> that.x && y <=> that.y
    case _ => false
  }

  override def toString: String = s"Point(x: $x, y: $y)"

  def ==(other: Point): Boolean = {
    x <=> other.x && y <=> other.y
  }

  /*
  scalar operations
   */
  def +(scalar: Double): Point = {
    set(
      x + scalar,
      y + scalar
    )
  }

  def -(scalar: Double): Point = {
    set(
      x - scalar,
      y - scalar
    )
  }

  def *(scalar: Double): Point = {
    set(
      x * scalar,
      y * scalar
    )
  }

  def /(scalar: Double): Point = {
    set(
      x / scalar,
      y / scalar
    )
  }

  /*
  Point operations
  Note: There is no such thing as "dividing" two vectors.
   */
  def +(other: Point): Point = {
    set(
      x + other.x,
      y + other.y
    )
  }

  def -(other: Point): Point = {
    set(
      x - other.x,
      y - other.y
    )
  }

  def *(other: Point): Point = {
    set(
      x * other.x,
      y * other.y
    )
  }

  def rotate(value: Double): Point = {
    val radians = value * (math.Pi * 2.0)
    val sin = math.sin(radians)
    val cos = math.cos(radians)
    set(
      x * cos - y * sin,
      x * sin + y * cos
    )
  }

}

class MutablePoint(var x: Double = 0.0, var y: Double = 0.0) extends Point {
  override def set(x: Double = x, y: Double = y): Point = {
    this.x = x
    this.y = y
    this
  }

  override def duplicate(): Point = new MutablePoint(x, y)

  override def isMutable: Boolean = true
  override def mutable: MutablePoint = this
  override def immutable: ImmutablePoint = ImmutablePoint(x, y)
}

case class ImmutablePoint(x: Double = 0.0, y: Double = 0.0) extends Point {
  override def set(x: Double, y: Double): Point = ImmutablePoint(x, y)

  override def duplicate(): Point = ImmutablePoint(x, y)

  override def isMutable: Boolean = false
  override def mutable: MutablePoint = new MutablePoint(x, y)
  override def immutable: ImmutablePoint = this
}

object Point {
  lazy val zero: Point = apply()

  def apply(x: Double = 0.0, y: Double = 0.0): Point = ImmutablePoint(x, y)
  def mutable(x: Double = 0.0, y: Double = 0.0): MutablePoint = new MutablePoint(x, y)
}