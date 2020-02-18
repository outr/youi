/*
package io.youi.gui.event

import io.youi.gui.Component
import io.youi.spatial.BoundingBox
import reactify.reaction.{Reaction, ReactionStatus}

class Pinch(component: Component, gestures: Gestures) extends Reaction[PinchEvent] {
  gestures.pointers.dragged.attach(dragging)

  private def dragging(pointer: Pointer): Unit = if (gestures.pointers.map.size == 2 && pointer.moved.distance != 0.0) {
    val other = gestures.pointers.map.find(_._1 != pointer.identifier).get._2

    val ox = other.move.local.x
    val oy = other.move.local.y
    val op = other.move.local
    val px = pointer.previous.local.x
    val py = pointer.previous.local.y
    val pp = pointer.previous.local
    val cx = pointer.move.local.x
    val cy = pointer.move.local.y
    val cp = pointer.move.local

    val pd = BoundingBox.distance(ox, oy, px, py)
    val cd = BoundingBox.distance(ox, oy, cx, cy)
    val deltaX = cx - px
    val deltaY = cy - py

    val deltaDistance = cd - pd
    val direction = if (deltaDistance > 0.0) {
      PinchDirection.Out
    } else {
      PinchDirection.In
    }
    val pe = PinchEvent(
      pointer,
      previous = PinchState(op, pp, pd),
      current = PinchState(op, cp, cd),
      deltaX = deltaX,
      deltaY = deltaY,
      deltaDistance = cd - pd,
      direction = direction
    )
    apply(pe, None)
  }

  override def apply(value: PinchEvent, previous: Option[PinchEvent]): ReactionStatus = ReactionStatus.Continue
}
*/
