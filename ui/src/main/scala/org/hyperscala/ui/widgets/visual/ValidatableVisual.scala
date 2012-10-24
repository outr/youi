package org.hyperscala.ui.widgets.visual

import org.powerscala.property.event.PropertyChangingEvent
import org.powerscala.bus.Routing

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ValidatableVisual[T] {
  this: Visual[T] =>

  def +=(validation: T => Option[T]) = property.listeners.synchronous {
    case evt: PropertyChangingEvent => validation(evt.newValue.asInstanceOf[T]) match {
      case Some(t) => Routing.Response(t)
      case None => Routing.Stop
    }
  }
}