package io.youi.component.event

import io.youi.component.Component
import reactify.{Channel, Var}

abstract class DragSupport[T](component: Component) {
  val dragging: Var[Option[T]] = Var(None)
  val dropped: Channel[Dropped[T]] = Channel[Dropped[T]]

  component.event.pointer.down.attach(checkForDown)
  component.event.pointer.up.attach(checkForUp)
  component.event.pointer.upOutside.attach(checkForUp)

  /**
    * Determines if there is draggable for this MouseEvent. This is called on mouse down events.
    *
    * @param mouseEvent the event that triggered this draggable check
    * @return optional T if there is a draggable for this mouse event
    */
  def draggable(mouseEvent: MouseEvent): Option[T]

  protected def checkForDown(mouseEvent: MouseEvent): Unit = {
    dragging := draggable(mouseEvent)
  }

  protected def checkForUp(mouseEvent: MouseEvent): Unit = dragging().foreach { value =>
    dropped := Dropped(mouseEvent, value)
    dragging := None
  }
}
