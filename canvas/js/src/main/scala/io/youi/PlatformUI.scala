package io.youi

import io.youi.drawable.Drawable
import io.youi.event.KeyEvent
import org.scalajs.dom.{Event, document, window}
import org.scalajs.dom.html.Div
import org.scalajs.dom.raw.KeyboardEvent
import reactify.{Val, Var}

object PlatformUI extends UI {
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

  override lazy val width: Val[Double] = Var[Double](window.innerWidth)
  override lazy val height: Val[Double] = Var[Double](window.innerHeight)

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

  def keyboardEvent2KeyEvent(evt: KeyboardEvent): KeyEvent = {
    val key = Key(evt.key)
    KeyEvent(
      key = key,
      repeat = evt.repeat,
      modifierState = (k: Key) => evt.getModifierState(k.value),
      preventDefault = () => evt.preventDefault(),
      defaultPrevented = () => evt.defaultPrevented,
      stopImmediatePropagation = () => evt.stopImmediatePropagation(),
      stopPropagation = () => evt.stopPropagation()
    )
  }

  override def createDrawable(): Drawable = new CanvasDrawable
}
