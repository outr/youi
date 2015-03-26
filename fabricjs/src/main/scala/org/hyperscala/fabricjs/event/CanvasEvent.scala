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