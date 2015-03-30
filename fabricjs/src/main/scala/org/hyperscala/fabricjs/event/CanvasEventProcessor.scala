package org.hyperscala.fabricjs.event

import org.hyperscala.fabricjs._
import org.hyperscala.javascript.JavaScriptContent
import org.powerscala.event.Listenable
import org.powerscala.hierarchy.ChildLike
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.powerscala.property.{Property, WriteProperty, ReadProperty}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CanvasEventProcessor[T <: CanvasEvent](name: String, val canvas: StaticCanvas)(implicit eventManifest: Manifest[T])
  extends StandardHierarchyEventProcessor[T](name)(canvas, eventManifest)
  with ReadProperty[JavaScriptContent]
  with WriteProperty[JavaScriptContent]
  with Listenable
  with ChildLike[StaticCanvas] {
  canvas._events = this :: canvas._events

  override protected def hierarchicalParent = canvas

  val js = new CanvasEventProperty(name, this)

  def apply() = js()

  def apply(v1: JavaScriptContent) = js(v1)
}

class CanvasEventProperty[T <: CanvasEvent](name: String, val p: CanvasEventProcessor[T]) extends Property[JavaScriptContent]()(p, implicitly[Manifest[JavaScriptContent]]) {
  /**
   * Concatenation of JavaScript support
   */
  def +=(content: JavaScriptContent) = if (value == null) {
    value = content
  } else {
    value = value + content
  }
}

class BeforeRenderEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[BeforeRenderEvent]("before:render", canvas)
class AfterRenderEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[AfterRenderEvent]("after:render", canvas)
class CanvasClearedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[CanvasClearedEvent]("canvas:cleared", canvas)
class ObjectAddedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[ObjectAddedEvent]("object:added", canvas)
class ObjectRemovedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[ObjectRemovedEvent]("object:removed", canvas)

class ObjectModifiedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[ObjectModifiedEvent]("object:modified", canvas)
class ObjectRotatingEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[ObjectRotatingEvent]("object:rotating", canvas)
class ObjectScalingEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[ObjectScalingEvent]("object:scaling", canvas)
class ObjectMovingEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[ObjectMovingEvent]("object:moving", canvas)
class ObjectSelectedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[ObjectSelectedEvent]("object:selected", canvas)
class BeforeSelectionClearedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[BeforeSelectionClearedEvent]("before:selection:cleared", canvas)
class SelectionClearedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[SelectionClearedEvent]("selection:cleared", canvas)
class SelectionCreatedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[SelectionCreatedEvent]("selection:created", canvas)
class PathCreatedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[PathCreatedEvent]("path:created", canvas)
class CanvasMouseDownEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[CanvasMouseDownEvent]("mouse:down", canvas)
class CanvasMouseMoveEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[CanvasMouseMoveEvent]("mouse:move", canvas)
class CanvasMouseUpEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[CanvasMouseUpEvent]("mouse:up", canvas)
class CanvasMouseOverEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[CanvasMouseOverEvent]("mouse:over", canvas)
class CanvasMouseOutEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[CanvasMouseOutEvent]("mouse:out", canvas)