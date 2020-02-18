package io.youi.gui.event

import io.youi.gui.Component
import reactify.{Channel, Val, Var}
import org.scalajs.{dom => jsdom}

class Events(component: Component) {
  def hasPointerSupport: Boolean = EventSupport.hasPointerSupport
  def hasTouchSupport: Boolean = EventSupport.hasTouchSupport

  lazy val change: Channel[jsdom.Event] = events("change")
  lazy val click: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.Click)
  lazy val doubleClick: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.DoubleClick)
  lazy val contextMenu: Channel[PointerEvent] = pointerChannel(PointerEvent.Type.ContextMenu)
  lazy val focus: Channel[jsdom.FocusEvent] = events("focus")
  lazy val blur: Channel[jsdom.FocusEvent] = events("blur")
  lazy val focusState: Val[Boolean] = {
    val o = Var[Boolean](false)
    focus.on(o @= true)
    blur.on(o @= false)
    o
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
  lazy val pointers: Pointers = new Pointers(this)
  object key {
    lazy val down: Channel[KeyEvent] = keyEvents("keydown", KeyEvent.Type.Down)
    lazy val press: Channel[KeyEvent] = keyEvents("keypress", KeyEvent.Type.Press)
    lazy val up: Channel[KeyEvent] = keyEvents("keyup", KeyEvent.Type.Up)
  }

  private def events[E <: jsdom.Event](eventType: String, stopPropagation: Boolean = false): Channel[E] = {
    val channel = Channel[E]
    component.element.addEventListener(eventType, (evt: E) => {
      if (stopPropagation) {
        evt.preventDefault()
        evt.stopPropagation()
      }
      channel @= evt
    })
    channel
  }

  private def pointerChannel(`type`: PointerEvent.Type): Channel[PointerEvent] = {
    val channel = Channel[PointerEvent]
    component.element.addEventListener(`type`.htmlTypeString, (evt: jsdom.MouseEvent) => {
      val pe = new PointerEvent(evt, `type`)
      channel @= pe
    })
    channel
  }

  private def wheelChannel(): Channel[WheelEvent] = {
    val channel = Channel[WheelEvent]
    component.element.addEventListener(PointerEvent.Type.Wheel.htmlTypeString, (evt: jsdom.WheelEvent) => {
      val we = new WheelEvent(evt)
      channel @= we
    })
    channel
  }

  private def keyEvents(eventType: String, `type`: KeyEvent.Type): Channel[KeyEvent] = {
    val originalEvents = events[jsdom.KeyboardEvent](eventType)
    val channel = Channel[KeyEvent]
    originalEvents.attach { ke =>
      channel @= new KeyEvent(ke, `type`)
    }
    channel
  }
}
