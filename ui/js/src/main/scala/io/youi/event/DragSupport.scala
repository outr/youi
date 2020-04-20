package io.youi.event

abstract class DragSupport[T](component: Component) {
  val value: Var[Option[T]] = Var(None)
  val drop: Channel[Dropped[T]] = Channel[Dropped[T]]

  def isDragging: Boolean = value().nonEmpty

  protected def gestures: Gestures = component.event.gestures

  gestures.pointers.added.attach { p =>
    if (gestures.pointers.map.size > 1) {
      value @= None
    } else {
      checkForDown(p)
    }
  }
  gestures.pointers.dragged.attach { p =>
    value.foreach(v => dragging(p, v))
  }
  gestures.pointers.removed.attach(checkForUp)

  /**
    * Determines if there is draggable for this MouseEvent. This is called on mouse down events.
    *
    * @param pointer the event that triggered this draggable check
    * @return optional T if there is a draggable for this mouse event
    */
  def draggable(pointer: Pointer): Option[T]

  def dragging(pointer: Pointer, value: T): Unit = {}

  def dropped(pointer: Pointer, value: T): Unit = {
    drop @= Dropped(pointer, value)
  }

  protected def checkForDown(pointer: Pointer): Unit = {
    value.static(draggable(pointer))
  }

  protected def checkForUp(pointer: Pointer): Unit = value().foreach { v =>
    dropped(pointer, v)
    value @= None
  }
}