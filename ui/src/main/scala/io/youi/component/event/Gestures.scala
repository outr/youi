package io.youi.component.event

import io.youi.component.Component
import io.youi.component.draw.BoundingBox
import reactify.{Observable, Val, Var}

class Gestures(component: Component) {
  import component.event.pointer

  private val _pointers = Var[Map[Int, Pointer]](Map.empty)

  val pointers: Val[Map[Int, Pointer]] = _pointers

  // TODO: `state` to represent up, tap, press, drag, pinch, swype, etc.

  pointer.down.attach(add)
  pointer.move.attach { evt =>
    if (evt.inside) {
      moved(evt)
    }
  }
  Observable.wrap(
    pointer.up,
    pointer.upOutside,
    pointer.cancel
  ).attach(remove)


  private def add(evt: MouseEvent): Unit = _pointers := _pointers() + (evt.identifier -> Pointer(evt.identifier, evt, evt))
  private def moved(evt: MouseEvent): Unit = pointers().get(evt.identifier).foreach { p =>
    val start = p.start
    val previous = p.move
    val current = evt

    val moved = Moved(previous, current)
    val movedFromStart = Moved(start, current)

    _pointers := _pointers() + (p.identifier -> p.copy(move = previous, moved = moved, movedFromStart = movedFromStart))
  }
  private def remove(evt: MouseEvent): Unit = _pointers := _pointers() - evt.identifier
}

case class Pointer(identifier: Int,
                   start: MouseEvent,
                   move: MouseEvent,
                   moved: Moved = Moved(0.0, 0.0, 0.0),
                   movedFromStart: Moved = Moved(0.0, 0.0, 0.0))

case class Moved(deltaX: Double, deltaY: Double, distance: Double)

object Moved {
  def apply(previous: MouseEvent, current: MouseEvent): Moved = {
    val deltaX = current.globalX - previous.globalX
    val deltaY = current.globalY - previous.globalY
    val distance = BoundingBox.distance(previous.globalX, previous.globalY, current.globalX, current.globalY)
    Moved(deltaX, deltaY, distance)
  }
}

// TODO: _touch: Var[Map[Int, Pointer]] to represent starting MouseEvent, current MouseEvent, etc.