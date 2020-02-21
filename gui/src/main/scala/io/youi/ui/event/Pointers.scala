package io.youi.ui.event

import reactify.{Channel, Val, Var}

class Pointers(event: Events) {
  private val _pointers = Var[Map[Int, Pointer]](Map.empty)

  val map: Val[Map[Int, Pointer]] = _pointers
  lazy val all: Val[Vector[Pointer]] = Val(_pointers.values.toVector)
  val added: Channel[Pointer] = Channel[Pointer]
  val dragged: Channel[Pointer] = Channel[Pointer]
  val removed: Channel[Pointer] = Channel[Pointer]

  def get(identifier: Int): Option[Pointer] = map().get(identifier)
  def apply(identifier: Int): Pointer = get(identifier).getOrElse(throw new RuntimeException(s"Unable to find pointer by id: $identifier."))

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

    added @= p
  }
  private def dragging(evt: PointerEvent): Unit = get(evt.identifier).foreach { p =>
    val updated = p.withEvent(evt)

    _pointers @= _pointers() + (p.identifier -> updated)
    dragged @= updated
  }
  private def remove(evt: PointerEvent): Unit = get(evt.identifier).foreach { p =>
    _pointers @= _pointers() - evt.identifier

    removed @= p.copy()
  }
}
