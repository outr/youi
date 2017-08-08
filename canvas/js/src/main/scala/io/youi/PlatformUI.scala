package io.youi

import org.scalajs.dom.{Event, document, window}
import org.scalajs.dom.html.Div
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
}
