package io.youi

import io.youi.component.Container
import io.youi.event.KeyEvent
import org.scalajs.dom.{Event, document, html, window}
import org.scalajs.dom.html.Div
import org.scalajs.dom.raw.{CanvasRenderingContext2D, KeyboardEvent}
import reactify._

class UI(canvasInstance: html.Canvas = dom.create[html.Canvas]("canvas")) {
  object canvas extends Container {
    override protected[youi] lazy val drawable: Drawable = new Drawable(canvasInstance)

    width.and(height).on(updateSize())
    canvasInstance.style.position = "absolute"
    canvasInstance.style.left = "0px"
    canvasInstance.style.top = "0px"
    hide()
    updateSize()
    document.body.appendChild(canvasInstance)

    AnimationFrame.delta.attach(update)

    override def draw(context: Context): Unit = {
      super.draw(context)

      val canvas = drawable.canvas
      val ctx = canvasInstance.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
      ctx.clearRect(0.0, 0.0, canvasInstance.width, canvasInstance.height)
      ctx.drawImage(canvas.asInstanceOf[html.Image], 0.0, 0.0, canvas.width, canvas.height)
    }

    def apply(): html.Canvas = canvasInstance

    def show(): Unit = canvasInstance.style.display = "block"
    def hide(): Unit = canvasInstance.style.display = "none"

    private def updateSize(): Unit = {
      canvasInstance.width = width().ceil.toInt
      canvasInstance.height = height().ceil.toInt
    }
  }

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