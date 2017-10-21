package io.youi.event

import io.youi._
import org.scalajs.{dom => jsdom}
import org.scalajs.dom.html
import reactify.Channel

import scala.scalajs.js

class HTMLEvents(element: html.Element) {
  def hasPointerSupport: Boolean = HTMLEvents.hasPointerSupport
  def hasTouchSupport: Boolean = HTMLEvents.hasTouchSupport

  lazy val change: Channel[jsdom.Event] = events("change")
  lazy val click: Channel[jsdom.MouseEvent] = events("click")
  lazy val doubleClick: Channel[jsdom.MouseEvent] = events("dblclick")
  lazy val contextMenu: Channel[jsdom.MouseEvent] = events("contextmenu")
  lazy val focus: Channel[jsdom.FocusEvent] = events("focus")
  lazy val blur: Channel[jsdom.FocusEvent] = events("blur")
  object key {
    lazy val down: Channel[KeyEvent] = keyEvents("keydown", KeyEvent.Type.Down)
    lazy val press: Channel[KeyEvent] = keyEvents("keypress", KeyEvent.Type.Press)
    lazy val up: Channel[KeyEvent] = keyEvents("keyup", KeyEvent.Type.Up)
  }
  object pointer {
    lazy val enter: Channel[jsdom.MouseEvent] = pointerEvents("enter")
    lazy val over: Channel[jsdom.MouseEvent] = pointerEvents("over")
    lazy val move: Channel[jsdom.MouseEvent] = pointerEvents("move")
    lazy val down: Channel[jsdom.MouseEvent] = pointerEvents("down")
    lazy val up: Channel[jsdom.MouseEvent] = pointerEvents("up")
    lazy val leave: Channel[jsdom.MouseEvent] = pointerEvents("leave")
    lazy val out: Channel[jsdom.MouseEvent] = pointerEvents("out")
    lazy val cancel: Channel[jsdom.MouseEvent] = pointerEvents("cancel")
    lazy val wheel: Channel[jsdom.WheelEvent] = events("wheel")
  }
  object touch {
    lazy val start: Channel[jsdom.TouchEvent] = touchEvents("start")
    lazy val move: Channel[jsdom.TouchEvent] = touchEvents("move")
    lazy val cancel: Channel[jsdom.TouchEvent] = touchEvents("cancel")
    lazy val end: Channel[jsdom.TouchEvent] = touchEvents("end")
  }

  protected def keyEvents(eventType: String, `type`: KeyEvent.Type): Channel[KeyEvent] = {
    val originalEvents = events[jsdom.KeyboardEvent](eventType)
    originalEvents.map(_.toKeyEvent(`type`))
  }

  protected def events[E <: jsdom.Event](eventType: String, stopPropagation: Boolean = false): Channel[E] = {
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

  protected def pointerEvents(eventType: String, stopPropagation: Boolean = false): Channel[jsdom.MouseEvent] = {
    val eventName = if (hasPointerSupport) {
      s"pointer$eventType"
    } else {
      s"mouse$eventType"
    }
    val channel = Channel[jsdom.MouseEvent]
    element.addEventListener(eventName, (evt: jsdom.MouseEvent) => {
      if (stopPropagation) {
        evt.preventDefault()
        evt.stopPropagation()
      }
      channel := evt
    })
    channel
  }

  protected def touchEvents(eventType: String, stopPropagation: Boolean = false): Channel[jsdom.TouchEvent] = {
    val eventName = s"touch$eventType"
    val channel = Channel[jsdom.TouchEvent]
    element.addEventListener(eventName, (evt: jsdom.TouchEvent) => {
      if (stopPropagation) {
        evt.preventDefault()
        evt.stopPropagation()
      }
      channel := evt
    })
    channel
  }
}

object HTMLEvents {
  lazy val hasPointerSupport: Boolean = js.typeOf(js.Dynamic.global.PointerEvent) != "undefined"
  lazy val hasTouchSupport: Boolean = js.typeOf(jsdom.document.documentElement.asInstanceOf[js.Dynamic].ontouchstart) != "undefined"
}