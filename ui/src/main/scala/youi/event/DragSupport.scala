package youi.event

import youi.component.Component
import reactify.{Channel, Var}

trait DragSupport[T] extends EventSupport {
  this: Component =>
  val dragValue: Var[Option[T]] = Var(None)
  val drop: Channel[DragDrop[T]] = Channel[DragDrop[T]]

  def draggable(pointer: Pointer): Option[T]
  def dragging(pointer: Pointer, value: T): Unit = ()

  event.pointers.added.attach { pointer =>
    draggable(pointer).foreach { value =>
      dragValue @= Some(value)
    }
  }

  event.pointers.dragged.attach { pointer =>
    dragValue().foreach { value =>
      dragging(pointer, value)
    }
  }

  event.pointers.removed.attach { pointer =>
    dragValue().foreach { value =>
      drop @= DragDrop(pointer, value)
      dragValue @= None
    }
  }
}

case class DragDrop[T](pointer: Pointer, value: T)
