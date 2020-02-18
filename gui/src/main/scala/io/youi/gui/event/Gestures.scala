package io.youi.gui.event

import io.youi.gui.Component
import reactify.{Channel, Val, Var}

class Gestures(component: Component, event: Events) {
  private lazy val _pointers = Var[Map[Int, Pointer]](Map.empty)

  object pointers {
    val map: Val[Map[Int, Pointer]] = _pointers
    lazy val all: Val[Vector[Pointer]] = Val(_pointers.values.toVector)
    val added: Channel[Pointer] = Channel[Pointer]
    val dragged: Channel[Pointer] = Channel[Pointer]
    val removed: Channel[Pointer] = Channel[Pointer]

    def get(identifier: Int): Option[Pointer] = map().get(identifier)
    def apply(identifier: Int): Pointer = get(identifier).getOrElse(throw new RuntimeException(s"Unable to find pointer by id: $identifier."))
  }

  lazy val tap: Tap = new Tap(component)
  lazy val click: Click = new Click(component, this)
  lazy val doubleClick: DoubleClick = new DoubleClick(component)
  lazy val longPress: LongPress = new LongPress(component)
  lazy val pinch: Pinch = new Pinch(component, this)

  // TODO: `state` to represent drag, swype, fling, pan, rotate

  if (EventSupport.hasTouchSupport) {
    event.touch.start.attach(add)
    event.touch.move.attach(dragging)
    event.touch.end.attach(remove)
  }
  event.pointer.down.attach(add)
  event.pointer.move.attach(dragging)

  (event.pointer.up & event.pointer.cancel).attach(remove)

  private def add(evt: PointerEvent): Unit = {
    val p = Pointer(evt.identifier, evt, evt, evt)
    _pointers @= _pointers() + (evt.identifier -> p)

    pointers.added @= p
  }
  private def dragging(evt: PointerEvent): Unit = pointers.get(evt.identifier).foreach { p =>
    val updated = p.withEvent(evt)

    _pointers @= _pointers() + (p.identifier -> updated)
    pointers.dragged @= updated
  }
  private def remove(evt: PointerEvent): Unit = pointers.get(evt.identifier).foreach { p =>
    _pointers @= _pointers() - evt.identifier

    pointers.removed @= p.copy()
  }
}
