package org.hyperscala.fabricjs.event

import org.hyperscala.fabricjs._
import org.hyperscala.javascript.JavaScriptContent
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.powerscala.property.{WriteProperty, ReadProperty}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ObjectEventProcessor[T <: ObjectEvent](name: String, obj: Object)(implicit eventManifest: Manifest[T])
  extends StandardHierarchyEventProcessor[T](name)(obj, eventManifest)
  with ReadProperty[JavaScriptContent]
  with WriteProperty[JavaScriptContent] {
  obj._events = this :: obj._events

  val js = new EventProperty(name)(obj)

  def apply() = js()

  def apply(v1: JavaScriptContent) = js(v1)
}

class AddedEventProcessor(obj: Object) extends ObjectEventProcessor[AddedEvent]("added", obj)
class RemovedEventProcessor(obj: Object) extends ObjectEventProcessor[RemovedEvent]("removed", obj)
class SelectedEventProcessor(obj: Object) extends ObjectEventProcessor[SelectedEvent]("selected", obj)
class ModifiedEventProcessor(obj: Object) extends ObjectEventProcessor[ModifiedEvent]("modified", obj)
class RotatingEventProcessor(obj: Object) extends ObjectEventProcessor[RotatingEvent]("rotating", obj)
class ScalingEventProcessor(obj: Object) extends ObjectEventProcessor[ScalingEvent]("scaling", obj)
class MovingEventProcessor(obj: Object) extends ObjectEventProcessor[MovingEvent]("moving", obj)
class MouseDownEventProcessor(obj: Object) extends ObjectEventProcessor[MouseDownEvent]("mousedown", obj)
class MouseUpEventProcessor(obj: Object) extends ObjectEventProcessor[MouseUpEvent]("mouseup", obj)