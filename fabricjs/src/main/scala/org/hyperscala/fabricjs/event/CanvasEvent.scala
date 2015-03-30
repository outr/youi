package org.hyperscala.fabricjs.event

import org.hyperscala.event.BrowserEvent
import org.hyperscala.fabricjs._
import org.hyperscala.html.tag.{Canvas => HTMLCanvas}

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait CanvasEvent extends BrowserEvent {
  def tag: HTMLCanvas
  def canvas: StaticCanvas
  def obj: Object
  def eventType: String
}

case class BeforeRenderEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends CanvasEvent
case class AfterRenderEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends CanvasEvent
case class CanvasClearedEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends CanvasEvent
case class ObjectAddedEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends CanvasEvent
case class ObjectRemovedEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends CanvasEvent

case class ObjectModifiedEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class ObjectRotatingEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class ObjectScalingEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class ObjectMovingEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class ObjectSelectedEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class BeforeSelectionClearedEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class SelectionClearedEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class SelectionCreatedEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class PathCreatedEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class CanvasMouseDownEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class CanvasMouseMoveEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class CanvasMouseUpEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class CanvasMouseOverEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent
case class CanvasMouseOutEvent(tag: HTMLCanvas, canvas: Canvas, obj: Object, eventType: String) extends CanvasEvent