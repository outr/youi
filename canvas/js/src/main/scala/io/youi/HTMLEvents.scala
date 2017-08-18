package io.youi

import io.youi.event.KeyEvent
import org.scalajs.dom._
import org.scalajs.dom.raw.Event
import reactify.Channel

class HTMLEvents(element: html.Element) {
  lazy val change: Channel[Event] = events("change")
  lazy val click: Channel[MouseEvent] = events("click")
  lazy val doubleClick: Channel[MouseEvent] = events("dblclick")
  lazy val contextMenu: Channel[MouseEvent] = events("contextmenu")
  lazy val focus: Channel[FocusEvent] = events("focus")
  lazy val blur: Channel[FocusEvent] = events("blur")
  object key {
    lazy val down: Channel[KeyEvent] = keyEvents("keydown", KeyEvent.Type.Down)
    lazy val press: Channel[KeyEvent] = keyEvents("keypress", KeyEvent.Type.Press)
    lazy val up: Channel[KeyEvent] = keyEvents("keyup", KeyEvent.Type.Up)
  }
  object mouse {
    lazy val enter: Channel[MouseEvent] = events("mouseenter")
    lazy val over: Channel[MouseEvent] = events("mouseover")
    lazy val move: Channel[MouseEvent] = events("mousemove")
    lazy val down: Channel[MouseEvent] = events("mousedown")
    lazy val up: Channel[MouseEvent] = events("mouseup")
    lazy val leave: Channel[MouseEvent] = events("mouseleave")
    lazy val out: Channel[MouseEvent] = events("mouseout")
    lazy val wheel: Channel[WheelEvent] = events("wheel")
  }

  protected def keyEvents(eventType: String, `type`: KeyEvent.Type): Channel[KeyEvent] = {
    val originalEvents = events[KeyboardEvent](eventType)
    originalEvents.map(ui.keyboardEvent2KeyEvent(_, `type`))
  }

  protected def events[E <: Event](eventType: String, stopPropagation: Boolean = false): Channel[E] = {
    val channel = Channel[E]
    element.addEventListener(eventType, (evt: E) => {
      if (stopPropagation) {
        evt.preventDefault()
        evt.stopPropagation()
      }
      channel := evt
    })
    channel
  }
}
