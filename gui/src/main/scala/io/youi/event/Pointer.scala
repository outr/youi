package io.youi.event

import reactify.Var

case class Pointer(identifier: Int,
                   start: PointerEvent,
                   move: PointerEvent,
                   previous: PointerEvent,
                   moved: Moved = Moved(0.0, 0.0, 0.0),
                   movedFromStart: Moved = Moved(0.0, 0.0, 0.0),
                   meanX: List[Double] = Nil,
                   meanY: List[Double] = Nil,
                   meanTime: List[Long] = Nil) {
  val time: Long = System.currentTimeMillis()
  lazy val (velocityX, velocityY) = {
    val count = meanX.size.toDouble
    val averageX = meanX.sum / count
    val averageY = meanY.sum / count
    val averageTime = (meanTime.sum / count) / 1000.0
    (averageX / averageTime, averageY / averageTime)
  }
  lazy val (deltaX, deltaY) = (move.global.x - previous.global.x) -> (move.global.y - previous.global.y)

  def elapsed: Long = time - start.time

  def withEvent(evt: PointerEvent): Pointer = {
    val moved = Moved(move, evt)
    val movedFromStart = Moved(start, evt)
    val mx = (moved.deltaX :: meanX).take(Pointer.sampleSize)
    val my = (moved.deltaY :: meanY).take(Pointer.sampleSize)
    val mt = (elapsed :: meanTime).take(Pointer.sampleSize)
    copy(
      move = evt,
      previous = move,
      moved = moved,
      movedFromStart = movedFromStart,
      meanX = mx,
      meanY = my,
      meanTime = mt
    )
  }

  override def toString: String = s"Pointer(id: $identifier, start: $start, move: $move, velocity: $velocityX x $velocityY, delta: $deltaX x $deltaY)"
}

object Pointer {
  /**
    * The number of samples to use to calculate acceleration and velocity.
    *
    * Defaults to 10
    */
  val sampleSize: Var[Int] = Var(10)
}