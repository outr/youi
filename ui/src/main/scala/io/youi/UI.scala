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
  }

//  object position {
//    lazy val x: Val[Double] = Val(0.0)
//    lazy val y: Val[Double] = Val(0.0)
//
//    lazy val left: Val[Double] = x
//    lazy val center: Val[Double] = Val(size.width / 2.0)
//    lazy val right: Val[Double] = Val(size.width)
//
//    lazy val top: Val[Double] = y
//    lazy val middle: Val[Double] = Val(size.height / 2.0)
//    lazy val bottom: Val[Double] = Val(size.height)
//  }
//
//  object size {
//    private val _width = Var[Double](window.innerWidth)
//    private val _height = Var[Double](window.innerHeight)
//
//    val width: Val[Double] = Val(_width)
//    val height: Val[Double] = Val(_height)
//
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
//  }
}