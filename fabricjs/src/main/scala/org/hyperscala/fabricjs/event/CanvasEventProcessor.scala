package org.hyperscala.fabricjs.event

import org.hyperscala.fabricjs._
import org.hyperscala.javascript.JavaScriptContent
import org.powerscala.hierarchy.event.StandardHierarchyEventProcessor
import org.powerscala.property.{WriteProperty, ReadProperty}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class CanvasEventProcessor[T <: CanvasEvent](name: String, canvas: StaticCanvas)(implicit eventManifest: Manifest[T])
  extends StandardHierarchyEventProcessor[T](name)(canvas, eventManifest)
  with ReadProperty[JavaScriptContent]
  with WriteProperty[JavaScriptContent] {
  canvas._events = this :: canvas._events

  val js = new EventProperty(name)(canvas)

  def apply() = js()

  def apply(v1: JavaScriptContent) = js(v1)
}

class BeforeRenderEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[BeforeRenderEvent]("before:render", canvas)
class AfterRenderEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[AfterRenderEvent]("after:render", canvas)
class CanvasClearedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[CanvasClearedEvent]("canvas:cleared", canvas)
class ObjectAddedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[ObjectAddedEvent]("object:added", canvas)
class ObjectRemovedEventProcessor(canvas: StaticCanvas) extends CanvasEventProcessor[ObjectRemovedEvent]("object:removed", canvas)