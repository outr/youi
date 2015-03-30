package org.hyperscala.fabricjs.event

import org.hyperscala.fabricjs._
import org.hyperscala.html.tag
import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.powerscala.event.Listenable
import org.powerscala.hierarchy.ChildLike
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.powerscala.json.{MapSupport, TypedSupport}
import org.powerscala.property.{Property, WriteProperty, ReadProperty}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class ObjectEventProcessor[T <: ObjectEvent](name: String, val obj: Object)(implicit eventManifest: Manifest[T])
  extends StandardHierarchyEventProcessor[T](name)(obj, eventManifest)
  with ReadProperty[JavaScriptContent]
  with WriteProperty[JavaScriptContent]
  with Listenable
  with ChildLike[Object] {
  ObjectEventProcessor
  
  obj._events = this :: obj._events

  override protected def hierarchicalParent = obj

  val js = new ObjectEventProperty(name, this)

  def sendToServer() = {
    obj.connected[StaticCanvas] {
      case canvas => js := JavaScriptString(s"FabricJS.objectEventToServer('${canvas.canvas.identity}', '${canvas.id}', '${obj.id}', '$name', options);")
    }
    this
  }

  def apply() = js()

  def apply(v1: JavaScriptContent) = js(v1)
}

object ObjectEventProcessor {
  CanvasEventProcessor

  register[AddedEvent]("added")
  register[RemovedEvent]("removed")
  register[SelectedEvent]("selected")
  register[ModifiedEvent]("modified")
  register[RotatingEvent]("rotating")
  register[ScalingEvent]("scaling")
  register[MovingEvent]("moving")
  register[MouseDownEvent]("mousedown")
  register[MouseUpEvent]("mouseup")

  private def register[Event <: ObjectEvent](alias: String)(implicit manifest: Manifest[Event]) = TypedSupport.register(alias, manifest.runtimeClass)
}

class ObjectEventProperty[T <: ObjectEvent](name: String, val p: ObjectEventProcessor[T]) extends Property[JavaScriptContent]()(p, implicitly[Manifest[JavaScriptContent]]) {
  /**
   * Concatenation of JavaScript support
   */
  def +=(content: JavaScriptContent) = if (value == null) {
    value = content
  } else {
    value = value + content
  }
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