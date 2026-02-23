package io.youi.event

import io.youi.component.Component
import reactify.{Channel, Var}

class Gestures(component: Component, events: Events) {
  val tap: Channel[Pointer] = Channel[Pointer]
  val longPress: Channel[Pointer] = Channel[Pointer]
  val doubleClick: Channel[Pointer] = Channel[Pointer]

  val movementThreshold: Var[Double] = Var(10.0)
  val longPressDuration: Var[Long] = Var(500L)
  val doubleClickWindow: Var[Long] = Var(300L)

  private var longPressTimer: Option[Int] = None
  private var lastTapTime: Long = 0L
  private var lastTapPointer: Option[Pointer] = None

  events.pointers.added.attach { pointer =>
    cancelLongPress()
    longPressTimer = Some(
      org.scalajs.dom.window.setTimeout(
        () => {
          longPressTimer = None
          events.pointers.get(pointer.identifier).foreach { p =>
            if (p.movedFromStart.distance < movementThreshold()) {
              longPress @= p
            }
          }
        },
        longPressDuration().toDouble
      )
    )
  }

  events.pointers.dragged.attach { pointer =>
    if (pointer.movedFromStart.distance >= movementThreshold()) {
      cancelLongPress()
    }
  }

  events.pointers.removed.attach { pointer =>
    cancelLongPress()
    if (pointer.movedFromStart.distance < movementThreshold() && pointer.elapsed < longPressDuration()) {
      val now = System.currentTimeMillis()
      lastTapPointer match {
        case Some(_) if (now - lastTapTime) < doubleClickWindow() =>
          doubleClick @= pointer
          lastTapPointer = None
          lastTapTime = 0L
        case _ =>
          lastTapPointer = Some(pointer)
          lastTapTime = now
          tap @= pointer
      }
    }
  }

  private def cancelLongPress(): Unit = {
    longPressTimer.foreach(org.scalajs.dom.window.clearTimeout)
    longPressTimer = None
  }
}
