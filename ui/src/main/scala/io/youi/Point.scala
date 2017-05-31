package io.youi

sealed trait Point {
  def x: Double
  def y: Double

  def set(x: Double, y: Double): Point
  def set(that: Point): Point = set(that.x, that.y)
  def duplicate(): Point
  override def equals(obj: scala.Any): Boolean = obj match {
    case that: Point => x == that.x && y == that.y
    case _ => false
  }
}

class MutablePoint(var x: Double = 0.0, var y: Double = 0.0) extends Point {
  override def set(x: Double = x, y: Double = y): Point = {
    this.x = x
    this.y = y
    this
  }

  override def duplicate(): Point = new MutablePoint(x, y)
}

case class ImmutablePoint(x: Double = 0.0, y: Double = 0.0) extends Point {
  override def set(x: Double, y: Double): Point = ImmutablePoint(x, y)

  override def duplicate(): Point = ImmutablePoint(x, y)
}

object Point {
  lazy val zero: Point = apply()

  def apply(x: Double = 0.0, y: Double = 0.0): Point = ImmutablePoint(x, y)
  def mutable(x: Double = 0.0, y: Double = 0.0): MutablePoint = new MutablePoint(x, y)
}