package org.hyperscala.fabricjs.event

import org.hyperscala.fabricjs._

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait ObjectEvent {
  def obj: Object
}

case class AddedEvent(obj: Object) extends ObjectEvent
case class RemovedEvent(obj: Object) extends ObjectEvent
case class SelectedEvent(obj: Object) extends ObjectEvent
case class ModifiedEvent(obj: Object) extends ObjectEvent
case class RotatingEvent(obj: Object) extends ObjectEvent
case class ScalingEvent(obj: Object) extends ObjectEvent
case class MovingEvent(obj: Object) extends ObjectEvent
case class MouseDownEvent(obj: Object) extends ObjectEvent
case class MouseUpEvent(obj: Object) extends ObjectEvent