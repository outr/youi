package io.youi.event

import io.youi.HTMLEvents
import io.youi.spatial.Point
import org.scalajs.dom.raw

import scala.scalajs.js

class PointerEvent private[event](val `type`: PointerEvent.Type,
                                  val x: Double,
                                  val y: Double,
                                  val globalX: Double,
                                  val globalY: Double,
                                  val htmlEvent: raw.MouseEvent) extends Event {
  lazy val local: Point = Point(x, y)
  lazy val global: Point = Point(globalX, globalY)

  val time: Long = System.currentTimeMillis()
  lazy val htmlPointerEvent: Option[HTMLPointerEvent] = if (HTMLEvents.hasPointerSupport) {
    Some(htmlEvent.asInstanceOf[HTMLPointerEvent])
  } else {
    None
  }
  lazy val identifier: Int = htmlPointerEvent.map(_.pointerId).getOrElse(1)
  lazy val width: Int = htmlPointerEvent.map(_.width).getOrElse(1)
  lazy val height: Int = htmlPointerEvent.map(_.height).getOrElse(1)
  lazy val pressure: Double = htmlPointerEvent.map(_.pressure).getOrElse(1.0)
  lazy val tangentialPressure: Double = htmlPointerEvent.map(_.tangentialPressure).getOrElse(0.0)
  lazy val tiltX: Double = htmlPointerEvent.map(_.tiltX).getOrElse(0.0)
  lazy val tiltY: Double = htmlPointerEvent.map(_.tiltY).getOrElse(0.0)
  lazy val twist: Double = htmlPointerEvent.map(_.twist).getOrElse(0.0)
  lazy val pointerType: String = htmlPointerEvent.map(_.pointerType).getOrElse("mouse")
  lazy val isPrimary: Boolean = htmlPointerEvent.forall(_.isPrimary)

  def stopPropagation(): Unit = htmlEvent.stopPropagation()
  def preventDefault(): Unit = htmlEvent.preventDefault()

  override def toString: String = s"PointerEvent(type: ${`type`}, local: $local, global: $global)"
}

object PointerEvent {
  def apply(`type`: Type, x: Double, y: Double, globalX: Double, globalY: Double, htmlEvent: raw.MouseEvent): PointerEvent = {
    new PointerEvent(`type`, x, y, globalX, globalY, htmlEvent)
  }

  sealed trait Type

  object Type {
    case object Click extends Type
    case object DoubleClick extends Type
    case object Move extends Type
    case object Enter extends Type
    case object Exit extends Type
    case object Down extends Type
    case object Up extends Type
    case object Cancel extends Type
    case object Wheel extends Type
  }
}

@js.native
trait HTMLPointerEvent extends js.Object {
  def pointerId: Int = js.native
  def width: Int = js.native
  def height: Int = js.native
  def pressure: Double = js.native
  def tangentialPressure: Double = js.native
  def tiltX: Double = js.native
  def tiltY: Double = js.native
  def twist: Double = js.native
  def pointerType: String = js.native
  def isPrimary: Boolean = js.native
}