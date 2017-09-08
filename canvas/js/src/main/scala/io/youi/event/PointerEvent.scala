package io.youi.event

import io.youi.component.Component
import io.youi.spatial.Point
import org.scalajs.dom.raw

import scala.scalajs.js

class PointerEvent private[event](val target: Component,
                                  val `type`: PointerEvent.Type,
                                  val x: Double,
                                  val y: Double,
                                  val globalX: Double,
                                  val globalY: Double,
                                  val htmlEvent: raw.UIEvent,
                                  val htmlEventType: HTMLEventType) extends Event {
  lazy val local: Point = Point(x, y)
  lazy val global: Point = Point(globalX, globalY)

  def isTouch: Boolean = htmlEventType == HTMLEventType.Touch
  def isPointer: Boolean = htmlEventType == HTMLEventType.Pointer
  def isMouse: Boolean = !isTouch

  def htmlMouseEvent: Option[raw.MouseEvent] = if (isMouse) {
    Some(htmlEvent.asInstanceOf[raw.MouseEvent])
  } else {
    None
  }

  def htmlPointerEvent: Option[HTMLPointerEvent] = if (isPointer) {
    Some(htmlEvent.asInstanceOf[HTMLPointerEvent])
  } else {
    None
  }

  def htmlTouchEvent: Option[raw.TouchEvent] = if (isTouch) {
    Some(htmlEvent.asInstanceOf[raw.TouchEvent])
  } else {
    None
  }

  val time: Long = System.currentTimeMillis()
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

  override def stopPropagation(): Unit = {
    super.stopPropagation()

    htmlEvent.stopPropagation()
  }
  def preventDefault(): Unit = htmlEvent.preventDefault()

  override def toString: String = s"PointerEvent(type: ${`type`}, local: $local, global: $global)"
}

object PointerEvent {
  def apply(target: Component, `type`: Type,
            x: Double,
            y: Double,
            globalX: Double,
            globalY: Double,
            htmlEvent: raw.UIEvent,
            htmlEventType: HTMLEventType): PointerEvent = {
    new PointerEvent(target, `type`, x, y, globalX, globalY, htmlEvent, htmlEventType)
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
    case object TouchStart extends Type
    case object TouchMove extends Type
    case object TouchCancel extends Type
    case object TouchEnd extends Type
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

sealed trait HTMLEventType

object HTMLEventType {
  case object Mouse extends HTMLEventType
  case object Pointer extends HTMLEventType
  case object Touch extends HTMLEventType
}