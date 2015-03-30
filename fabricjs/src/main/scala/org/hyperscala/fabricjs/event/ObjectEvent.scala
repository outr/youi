package org.hyperscala.fabricjs.event

import org.hyperscala.event.BrowserEvent
import org.hyperscala.fabricjs._
import org.hyperscala.html.tag.{Canvas => HTMLCanvas}

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait ObjectEvent extends BrowserEvent {
  def tag: HTMLCanvas
  def canvas: StaticCanvas
  def obj: Object
  def eventType: String
}

case class AddedEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends ObjectEvent
case class RemovedEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends ObjectEvent
case class SelectedEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends ObjectEvent
case class ModifiedEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends ObjectEvent
case class RotatingEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends ObjectEvent
case class ScalingEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends ObjectEvent
case class MovingEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends ObjectEvent
case class MouseDownEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends ObjectEvent
case class MouseUpEvent(tag: HTMLCanvas, canvas: StaticCanvas, obj: Object, eventType: String) extends ObjectEvent