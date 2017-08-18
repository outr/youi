package io.youi

import io.youi.component.{Container, Renderer}
import io.youi.event.KeyEvent
import org.scalajs.dom.{Event, document, html, window}
import org.scalajs.dom.html.Div
import org.scalajs.dom.raw.KeyboardEvent
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