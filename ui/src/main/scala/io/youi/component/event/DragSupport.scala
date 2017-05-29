package io.youi.component.event

import io.youi.component.Component
import reactify.{Channel, Var}

abstract class DragSupport[T](component: Component) {
  val value: Var[Option[T]] = Var(None)
  val dropped: Channel[Dropped[T]] = Channel[Dropped[T]]

  def isDragging: Boolean = value().nonEmpty

  component.event.pointer.down.attach(checkForDown)
  component.event.pointer.up.attach(checkForUp)
  component.event.pointer.upOutside.attach(checkForUp)
  component.event.pointer.move.attach { mouseEvent =>
    value.foreach(v => dragging(mouseEvent, v))
  }

  /**
    * Determines if there is draggable for this MouseEvent. This is called on mouse down events.
    *
    * @param mouseEvent the event that triggered this draggable check
    * @return optional T if there is a draggable for this mouse event
    */
  def draggable(mouseEvent: MouseEvent): Option[T]

  def dragging(mouseEvent: MouseEvent, value: T): Unit = {}

  protected def checkForDown(mouseEvent: MouseEvent): Unit = {
    value := draggable(mouseEvent)
  }

  protected def checkForUp(mouseEvent: MouseEvent): Unit = value().foreach { v =>
    dropped := Dropped(mouseEvent, v)
    value := None
  }
}
