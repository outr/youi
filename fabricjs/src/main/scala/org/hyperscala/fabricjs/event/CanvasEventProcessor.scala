package org.hyperscala.fabricjs.event

import org.hyperscala.fabricjs._
import org.hyperscala.javascript.{JavaScriptString, JavaScriptContent}
import org.powerscala.event.Listenable
import org.powerscala.hierarchy.ChildLike
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.powerscala.json.{MapSupport, TypedSupport}
import org.powerscala.property.{Property, WriteProperty, ReadProperty}
import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CanvasEventProcessor[T <: CanvasEvent](name: String, val canvas: StaticCanvas)(implicit eventManifest: Manifest[T])
  extends StandardHierarchyEventProcessor[T](name)(canvas, eventManifest)
  with ReadProperty[JavaScriptContent]
  with WriteProperty[JavaScriptContent]
  with Listenable
  with ChildLike[StaticCanvas] {
  CanvasEventProcessor

  canvas._events = this :: canvas._events

  override protected def hierarchicalParent = canvas

  val js = new CanvasEventProperty(name, this)

  def sendToServer() = {
    js := JavaScriptString(s"FabricJS.canvasEventToServer('${canvas.canvas.identity}', '${canvas.id}', '$name', options);")
    this
  }

  def apply() = js()

  def apply(v1: JavaScriptContent) = js(v1)
}

object CanvasEventProcessor {
  MapSupport.j2o.on {
    case m if m.contains("canvasId") && m.contains("tag") => {
      val t = m("tag").asInstanceOf[tag.Canvas]
      val canvas = StaticCanvas.get(t).orNull
      val obj = canvas.getById[Object](m("objectId").asInstanceOf[String])
      m ++ Map("canvas" -> canvas, "obj" -> obj, "eventType" -> m("type"))
    }
    case m => m
  }

  register[BeforeRenderEvent]("before:render")
  register[AfterRenderEvent]("after:render")
  register[CanvasClearedEvent]("canvas:cleared")
  register[ObjectAddedEvent]("object:added")
  register[ObjectRemovedEvent]("object:removed")

  register[ObjectModifiedEvent]("object:modified")
  register[ObjectRotatingEvent]("object:rotating")
  register[ObjectScalingEvent]("object:scaling")
  register[ObjectMovingEvent]("object:moving")
  register[ObjectSelectedEvent]("object:selected")
  register[BeforeSelectionClearedEvent]("before:selection:cleared")
  register[SelectionClearedEvent]("selection:cleared")
  register[SelectionCreatedEvent]("selection:created")
  register[PathCreatedEvent]("path:created")
  register[CanvasMouseDownEvent]("mouse:down")
  register[CanvasMouseMoveEvent]("mouse:move")
  register[CanvasMouseUpEvent]("mouse:up")
  register[CanvasMouseOverEvent]("mouse:over")
  register[CanvasMouseOutEvent]("mouse:out")

  private def register[Event <: CanvasEvent](alias: String)(implicit manifest: Manifest[Event]) = TypedSupport.register(alias, manifest.runtimeClass)
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