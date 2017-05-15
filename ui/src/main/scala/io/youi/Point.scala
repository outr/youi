package io.youi

class Point(var x: Double = 0.0, var y: Double = 0.0) {
  def set(x: Double = x, y: Double = y): Point = {
    this.x = x
    this.y = y
    this
  }

  def set(that: Point): Point = {
    set(that.x, that.y)
  }

  def duplicate(): Point = new Point(x, y)

  override def equals(obj: scala.Any): Boolean = obj match {
    case that: Point => x == that.x && y == that.y
    case _ => false
  }
}
