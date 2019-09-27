package io.youi.event

import io.youi._
import io.youi.component.Component
import io.youi.net.URL
import org.scalajs.dom.raw.EventTarget
import org.scalajs.{dom => jsdom}
import reactify.{Channel, Val, Var}

import scala.scalajs.js

trait EventSupport {
  protected def component: Component

  def hasPointerSupport: Boolean = HTMLEvents.hasPointerSupport
  def hasTouchSupport: Boolean = HTMLEvents.hasTouchSupport

  lazy val gestures: Gestures = new Gestures(component)

  lazy val change: Channel[jsdom.Event] = events("change")
  lazy val click: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Click)
  lazy val doubleClick: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.DoubleClick)
  lazy val contextmenu: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.ContextMenu)
  lazy val focus: Channel[jsdom.FocusEvent] = events("focus")
  lazy val blur: Channel[jsdom.FocusEvent] = events("blur")
  lazy val focusState: Val[Boolean] = {
    val o = Var[Boolean](false)
    focus.on(o @= true)
    blur.on(o @= false)
    o
  }
  object key {
    lazy val down: Channel[KeyEvent] = keyEvents("keydown", KeyEvent.Type.Down)
    lazy val press: Channel[KeyEvent] = keyEvents("keypress", KeyEvent.Type.Press)
    lazy val up: Channel[KeyEvent] = keyEvents("keyup", KeyEvent.Type.Up)
  }
  object pointer {
    lazy val move: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Move)
    lazy val enter: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Enter)
    lazy val exit: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Exit)
    lazy val down: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Down)
    lazy val up: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Up)
    lazy val cancel: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Cancel)
    lazy val wheel: Channel[WheelEvent] = wheelChannel()

    lazy val (x: Val[Double], y: Val[Double], overState: Val[Boolean]) = {
      val px = Var[Double](0.0)
      val py = Var[Double](0.0)
      val o = Var[Boolean](false)
      pointer.enter.on(o @= true)
      pointer.exit.on(o @= false)
      pointer.move.attach { evt =>
        px @= evt.local.x
        py @= evt.local.y
      }
      (px, py, o)
    }

    lazy val downState: Val[Boolean] = {
      val d = Var[Boolean](false)
      pointer.down.on(d @= true)
      pointer.up.on(d @= false)
      pointer.exit.on(d @= false)
      d
    }
  }
  object touch {
    lazy val start: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.TouchStart)
    lazy val move: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.TouchMove)
    lazy val cancel: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.TouchCancel)
    lazy val end: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.TouchEnd)
  }

  protected def keyEvents(eventType: String, `type`: KeyEvent.Type): Channel[KeyEvent]
  protected def events[E <: jsdom.Event](eventType: String, stopPropagation: Boolean = false): Channel[E]
  protected def pointerChannel(`type`: PointerEvent.Type): Channel[PointerEvent]
  protected def wheelChannel(): Channel[WheelEvent]

  def link(url: URL): Unit = {
    component.cursor @= Cursor.Pointer
    click.on(History.push(url))
  }

  def link(path: String): Unit = link(History.url().withPart(path))
}

class HTMLEvents(override protected val component: Component, element: EventTarget) extends EventSupport {
  override protected def keyEvents(eventType: String, `type`: KeyEvent.Type): Channel[KeyEvent] = {
    val originalEvents = events[jsdom.KeyboardEvent](eventType)
    val channel = Channel[KeyEvent]
    originalEvents.attach { ke =>
      ke.toKeyEvent(component, `type`).foreach(channel @= _)
    }
    channel
  }

  override protected def events[E <: jsdom.Event](eventType: String, stopPropagation: Boolean = false): Channel[E] = {
    val channel = Channel[E]
    element.addEventListener(eventType, (evt: E) => {
      if (stopPropagation) {
        evt.preventDefault()
        evt.stopPropagation()
      }
      channel @= evt
    })
    channel
  }

  override protected def pointerChannel(`type`: PointerEvent.Type): Channel[PointerEvent] = {
    val channel = Channel[PointerEvent]
    element.addEventListener(`type`.htmlTypeString, (evt: jsdom.MouseEvent) => {
      try {
        val p = PointerEvent(
          target = component,
          `type` = `type`,
          x = if (`type`.isTouch) 0.0 else evt.clientX,
          y = if (`type`.isTouch) 0.0 else evt.clientY,
          globalX = if (`type`.isTouch) 0.0 else evt.pageX,
          globalY = if (`type`.isTouch) 0.0 else evt.pageY,
          htmlEvent = evt,
          htmlEventType = `type`.htmlType
        )
        channel @= p
      } catch {
        case t: Throwable => throw new RuntimeException(s"Error while creating pointer event for ${`type`}", t)
      }
    })
    channel
  }

  override protected def wheelChannel(): Channel[WheelEvent] = {
    val channel = Channel[WheelEvent]
    element.addEventListener(PointerEvent.Type.Wheel.htmlTypeString, (evt: jsdom.WheelEvent) => {
      val mode = evt.deltaMode match {
        case 0 => DeltaMode.Pixel
        case 1 => DeltaMode.Line
        case 2 => DeltaMode.Page
      }
      val delta = WheelDelta(evt.deltaX, evt.deltaY, evt.deltaZ, mode, evt)
      val p = WheelEvent(
        target = component,
        x = evt.clientX,
        y = evt.clientY,
        globalX = evt.pageX,
        globalY = evt.pageY,
        delta = delta
      )
      channel @= p
    })
    channel
  }
}

object HTMLEvents {
  lazy val hasPointerSupport: Boolean = js.typeOf(js.Dynamic.global.PointerEvent) != "undefined"
  lazy val hasTouchSupport: Boolean = js.typeOf(jsdom.document.documentElement.asInstanceOf[js.Dynamic].ontouchstart) != "undefined"
}