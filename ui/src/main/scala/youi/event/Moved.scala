package youi.event

import youi.spatial.BoundingBox

case class Moved(deltaX: Double, deltaY: Double, distance: Double)

object Moved {
  def apply(previous: PointerEvent, current: PointerEvent): Moved = {
    val deltaX = current.global.x - previous.global.x
    val deltaY = current.global.y - previous.global.y
    val distance = BoundingBox.distance(previous.global.x, previous.global.y, current.global.x, current.global.y)
    Moved(deltaX, deltaY, distance)
  }
}