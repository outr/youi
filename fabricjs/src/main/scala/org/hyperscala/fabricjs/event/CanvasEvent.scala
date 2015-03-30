package org.hyperscala.fabricjs.event

import org.hyperscala.fabricjs._

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait CanvasEvent {
  def canvas: StaticCanvas
}

case class BeforeRenderEvent(canvas: StaticCanvas) extends CanvasEvent
case class AfterRenderEvent(canvas: StaticCanvas) extends CanvasEvent
case class CanvasClearedEvent(canvas: StaticCanvas) extends CanvasEvent
case class ObjectAddedEvent(canvas: StaticCanvas) extends CanvasEvent
case class ObjectRemovedEvent(canvas: StaticCanvas) extends CanvasEvent

case class ObjectModifiedEvent(canvas: Canvas) extends CanvasEvent
case class ObjectRotatingEvent(canvas: Canvas) extends CanvasEvent
case class ObjectScalingEvent(canvas: Canvas) extends CanvasEvent
case class ObjectMovingEvent(canvas: Canvas) extends CanvasEvent
case class ObjectSelectedEvent(canvas: Canvas) extends CanvasEvent
case class BeforeSelectionClearedEvent(canvas: Canvas) extends CanvasEvent
case class SelectionClearedEvent(canvas: Canvas) extends CanvasEvent
case class SelectionCreatedEvent(canvas: Canvas) extends CanvasEvent
case class PathCreatedEvent(canvas: Canvas) extends CanvasEvent
case class CanvasMouseDownEvent(canvas: Canvas) extends CanvasEvent
case class CanvasMouseMoveEvent(canvas: Canvas) extends CanvasEvent
case class CanvasMouseUpEvent(canvas: Canvas) extends CanvasEvent
case class CanvasMouseOverEvent(canvas: Canvas) extends CanvasEvent
case class CanvasMouseOutEvent(canvas: Canvas) extends CanvasEvent