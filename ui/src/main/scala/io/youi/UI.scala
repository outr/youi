package io.youi

import org.scalajs.dom._
import reactify.{Val, Var}
import scribe.Logging
import io.youi.hypertext.{Component, HTMLContainer}
import org.scalajs.dom.html.{Div, Element}

object UI extends HTMLContainer with Component with Logging {
  def name: String = getClass.getSimpleName.replaceAllLiterally("$", "")

  override protected[youi] val element: Element = document.body

  val title: Var[String] = Var(name)

  lazy val ppi: Double = {
    val div = dom.create[Div]("div")
    div.style.width = "1in"
    document.body.appendChild(div)
    try {
      element.offsetWidth
    } finally {
      document.body.removeChild(div)
    }
  }

  // TODO: either make sure this actually gets called or automatically call it
  override def init(): Unit = {
    super.init()

    title.attachAndFire { value =>
      document.title = value
    }

    val w = window.innerWidth
    val h = window.innerHeight
    actualWidth := w
    actualHeight := h

    size.width := actualWidth
    size.height := actualHeight

    AnimationFrame.delta.attach { delta =>
      update(delta)
    }
  }

  override protected def determineActualWidth: Double = window.innerWidth

  override protected def determineActualHeight: Double = window.innerHeight

  private var running = false
  window.addEventListener("resize", (evt: Event) => {
    if (!running) {
      running = true

      window.requestAnimationFrame((d: Double) => {
        val w = window.innerWidth
        val h = window.innerHeight
        actualWidth := w
        actualHeight := h

        running = false
      })
    }
  })
}