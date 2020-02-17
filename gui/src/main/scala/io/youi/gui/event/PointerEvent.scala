package io.youi.gui.event

import io.youi.gui.Key
import io.youi.spatial.Point

import org.scalajs.{dom => jsdom}
import scala.scalajs.js.|

class PointerEvent(underlying: jsdom.MouseEvent | HTMLPointerEvent | jsdom.TouchEvent,
                   val `type`: PointerEvent.Type) extends HTMLEvent(underlying.asInstanceOf[jsdom.UIEvent]) {
  lazy val local: Point = if (isMouse) {
    val e = underlying.asInstanceOf[jsdom.MouseEvent]
    Point(e.clientX, e.clientY)
  } else {
    Point.zero
  }
  lazy val global: Point = if (isMouse) {
    val e = underlying.asInstanceOf[jsdom.MouseEvent]
    Point(e.pageX, e.pageY)
  } else {
    Point.zero
  }

  private lazy val hpe: Option[HTMLPointerEvent] = if (isPointer) {
    Some(underlying.asInstanceOf[HTMLPointerEvent])
  } else {
    None
  }

  def isTouch: Boolean = `type`.isTouch
  def isPointer: Boolean = `type`.isPointer
  def isMouse: Boolean = `type`.isMouse
  def identifier: Int = hpe.map(_.pointerId).getOrElse(1)
  def width: Int = hpe.map(_.width).getOrElse(1)
  def height: Int = hpe.map(_.height).getOrElse(1)
  def pressure: Double = hpe.map(_.pressure).getOrElse(1.0)
  def tangentialPressure: Double = hpe.map(_.tangentialPressure).getOrElse(0.0)
  def tiltX: Double = hpe.map(_.tiltX).getOrElse(0.0)
  def tiltY: Double = hpe.map(_.tiltY).getOrElse(0.0)
  def twist: Double = hpe.map(_.twist).getOrElse(0.0)
  def pointerType: String = hpe.map(_.pointerType).getOrElse("mouse")
  def isPrimary: Boolean = hpe.forall(_.isPrimary)

  private def modifierState(key: Key): Boolean = underlying match {
    case e: jsdom.MouseEvent => e.getModifierState(key.value)
    case _ => false
  }

  def altPressed: Boolean = modifierState(Key.Alt)
  def altGraphPressed: Boolean = modifierState(Key.AltGraph)
  def controlPressed: Boolean = modifierState(Key.Control)
  def shiftPressed: Boolean = modifierState(Key.Shift)

  def capsLockOn: Boolean = modifierState(Key.CapsLock)
  def numLockOn: Boolean = modifierState(Key.NumLock)
  def scrollLockOn: Boolean = modifierState(Key.ScrollLock)
}

object PointerEvent {
  sealed abstract class Type(`type`: String, includePrefix: Boolean = true) {
    lazy val htmlTypeString: String = if (includePrefix) {
      val prefix = if (EventSupport.hasPointerSupport) {
        "pointer"
      } else {
        "mouse"
      }
      s"$prefix${`type`}"
    } else {
      `type`
    }
    def htmlType: HTMLEventType = htmlTypeString match {
      case s if s.startsWith("pointer") => HTMLEventType.Pointer
      case s if s.startsWith("touch") => HTMLEventType.Touch
      case _ => HTMLEventType.Mouse
    }

    def isTouch: Boolean = htmlType == HTMLEventType.Touch
    def isPointer: Boolean = htmlType == HTMLEventType.Pointer
    def isMouse: Boolean = !isTouch
  }

  object Type {
    case object Click extends Type("click", includePrefix = false)
    case object DoubleClick extends Type("dblclick", includePrefix = false)
    case object ContextMenu extends Type("contextmenu", includePrefix = false)
    case object Move extends Type("move")
    case object Enter extends Type("enter")
    case object Exit extends Type("leave")
    case object Down extends Type("down")
    case object Up extends Type("up")
    case object Cancel extends Type("cancel")
    case object Wheel extends Type("wheel", includePrefix = false)
    case object TouchStart extends Type("touchstart", includePrefix = false)
    case object TouchMove extends Type("touchmove", includePrefix = false)
    case object TouchCancel extends Type("touchcancel", includePrefix = false)
    case object TouchEnd extends Type("touchend", includePrefix = false)
  }
}