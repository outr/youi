package io.youi.component.event

import io.youi.Point
import io.youi.component.Component
import io.youi.component.draw.BoundingBox
import reactify.Observable

class PinchSupport(component: Component) extends Observable[PinchEvent] {
  private var cache = Map.empty[Int, MouseEvent]

  component.event.pointer.down.attach(down)
  component.event.pointer.move.attach(move)
  Observable.wrap(
    component.event.pointer.up,
    component.event.pointer.upOutside,
    component.event.pointer.cancel,
    component.event.pointer.out
  ).attach(up)

  private def down(evt: MouseEvent): Unit = {
    cache += evt.identifier -> evt
  }
  private def move(evt: MouseEvent): Unit = if (cache.size == 2) {
    val p = cache.values.toVector
    val pi1 = p(0).identifier
    val px1 = p(0).x
    val py1 = p(0).y
    val pp1 = p(0).local
    val pi2 = p(1).identifier
    val px2 = p(1).x
    val py2 = p(1).y
    val pp2 = p(1).local
    val pd = BoundingBox.distance(px1, py1, px2, py2)
    val (cd, cp1, cp2, deltaX, deltaY) = if (pi1 == evt.identifier) {
      (BoundingBox.distance(evt.x, evt.y, px2, py2), evt.local, pp2, evt.x - px2, evt.y - py2)
    } else {
      (BoundingBox.distance(px1, py1, evt.x, evt.y), pp1, evt.local, evt.x - px1, evt.y - py1)
    }
    cache += evt.identifier -> evt

    val deltaDistance = cd - pd
    val direction = if (deltaDistance > 0.0) {
      PinchDirection.Out
    } else {
      PinchDirection.In
    }

    val pe = PinchEvent(
      previous = PinchState(pp1, pp2, pd),
      current = PinchState(cp1, cp2, cd),
      deltaX = deltaX,
      deltaY = deltaY,
      deltaDistance = cd - pd,
      direction = direction
    )

    scribe.info(s"Pinch! $pe")
    fire(pe)
  }
  private def up(evt: MouseEvent): Unit = {
    cache -= evt.identifier
  }
}

case class PinchEvent(previous: PinchState,
                      current: PinchState,
                      deltaX: Double,
                      deltaY: Double,
                      deltaDistance: Double,
                      direction: PinchDirection)

case class PinchState(point1: Point, point2: Point, distance: Double)

sealed trait PinchDirection

object PinchDirection {
  case object In extends PinchDirection
  case object Out extends PinchDirection
}