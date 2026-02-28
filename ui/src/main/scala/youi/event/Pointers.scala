package youi.event

import reactify.{Channel, Val, Var}

class Pointers(event: Events) {
  private val _pointers = Var[Map[Int, Pointer]](Map.empty)

  val map: Val[Map[Int, Pointer]] = _pointers
  lazy val all: Val[Vector[Pointer]] = Val(_pointers().values.toVector)
  val added: Channel[Pointer] = Channel[Pointer]
  val dragged: Channel[Pointer] = Channel[Pointer]
  val removed: Channel[Pointer] = Channel[Pointer]

  def get(identifier: Int): Option[Pointer] = map().get(identifier)
  def apply(identifier: Int): Pointer = get(identifier).getOrElse(throw new RuntimeException(s"Unable to find pointer by id: $identifier."))

  event.pointer.down.attach(pointerDown)
  event.pointer.move.attach(pointerMove)
  event.pointer.up.attach(pointerUp)
  event.pointer.cancel.attach(pointerUp)
  event.pointer.out.attach(pointerUp)
  event.pointer.leave.attach(pointerUp)

  private def pointerDown(evt: PointerEvent): Unit = {
    val p = Pointer(evt.identifier, evt, evt, evt)
    _pointers @= _pointers() + (evt.identifier -> p)

    added @= p
  }

  private def pointerMove(evt: PointerEvent): Unit = get(evt.identifier).foreach { p =>
    val updated = p.withEvent(evt)

    _pointers @= _pointers() + (p.identifier -> updated)
    dragged @= updated
  }

  private def pointerUp(evt: PointerEvent): Unit = get(evt.identifier).foreach { p =>
    _pointers @= _pointers() - evt.identifier

    removed @= p.copy()
  }
}
