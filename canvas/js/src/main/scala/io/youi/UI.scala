package io.youi

import io.youi.component.{Container, Renderer}
import io.youi.event.{KeyEvent, Mouse}
import org.scalajs.dom.{Event, document, html, window}
import org.scalajs.dom.html.Div
import org.scalajs.dom.raw.{KeyboardEvent, MouseEvent}
import reactify._

class UI(canvas: html.Canvas = dom.create[html.Canvas]("canvas")) {
  val renderer: Renderer = new Renderer(canvas)

  lazy val ppi: Double = {
    val div = dom.create[Div]("div")
    div.style.width = "1in"
    document.body.appendChild(div)
    try {
      div.offsetWidth
    } finally {
      document.body.removeChild(div)
    }
  }

  document.body.addEventListener("move", (evt: MouseEvent) => {
    Mouse.x.asInstanceOf[Var[Double]] := evt.clientX
    Mouse.y.asInstanceOf[Var[Double]] := evt.clientY
  })

  canvas.event.mouse.wheel.attach { evt =>
    val mode: DeltaMode = evt.deltaMode match {
      case 0x00 => DeltaMode.Pixel
      case 0x01 => DeltaMode.Line
      case 0x02 => DeltaMode.Page
    }
    val d = WheelDelta(evt.deltaX, evt.deltaY, evt.deltaZ, mode)
    Mouse.wheel := d
  }

  lazy val width: Val[Double] = Var[Double](window.innerWidth)
  lazy val height: Val[Double] = Var[Double](window.innerHeight)

  private var running = false
  window.addEventListener("resize", (_: Event) => {
    if (!running) {
      running = true
      window.requestAnimationFrame((_: Double) => {
        val w = window.innerWidth
        val h = window.innerHeight
        width.asInstanceOf[Var[Double]] := w
        height.asInstanceOf[Var[Double]] := h

        running = false
      })
    }
  })

  def keyboardEvent2KeyEvent(evt: KeyboardEvent, `type`: KeyEvent.Type): KeyEvent = {
    val key = Key(evt.key)
    KeyEvent(
      `type` = `type`,
      key = key,
      repeat = evt.repeat,
      modifierState = (k: Key) => evt.getModifierState(k.value),
      preventDefault = () => evt.preventDefault(),
      defaultPrevented = () => evt.defaultPrevented,
      stopImmediatePropagation = () => evt.stopImmediatePropagation(),
      stopPropagation = () => evt.stopPropagation()
    )
  }
  object position {
    val x: Val[Double] = Val(0.0)
    val y: Val[Double] = Val(0.0)

    val left: Val[Double] = x
    val center: Val[Double] = Val(width / 2.0)
    val right: Val[Double] = width

    val top: Val[Double] = y
    val middle: Val[Double] = Val(height / 2.0)
    val bottom: Val[Double] = height
  }
}