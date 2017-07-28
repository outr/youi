package io.youi.component.draw

case class TouchData(deltaX: Double, deltaY: Double, distance: Double) {
  override def toString: String = s"TouchData(deltaX: $deltaX, deltaY: $deltaY, distance: $distance)"
}
