package io.youi

import org.scalajs.dom._
import com.outr.props.{Val, Var}
import io.youi.html.HTMLContainer
import org.scalajs.dom.html.Element

trait UI extends HTMLContainer {
  def name: String = getClass.getSimpleName.replaceAllLiterally("$", "")

  override protected[youi] val element: Element = document.body

  def ui: UI = this
  val title: Var[String] = Var(name)

  object position {
    lazy val x: Val[Double] = Val(0.0)
    lazy val y: Val[Double] = Val(0.0)

    lazy val left: Val[Double] = x
    lazy val center: Val[Double] = Val(size.width / 2.0)
    lazy val right: Val[Double] = Val(size.width)

    lazy val top: Val[Double] = y
    lazy val middle: Val[Double] = Val(size.height / 2.0)
    lazy val bottom: Val[Double] = Val(size.height)
  }

  object size {
    private val _width = Var[Double](window.innerWidth)
    private val _height = Var[Double](window.innerHeight)

    val width: Val[Double] = _width.toVal
    val height: Val[Double] = _height.toVal

    private var running = false
    window.addEventListener("resize", (evt: Event) => {
      if (!running) {
        running = true

        window.requestAnimationFrame((d: Double) => {
          val w = window.innerWidth
          val h = window.innerHeight
          if (_width() != w) {
            _width := w
          }
          if (_height() != h) {
            _height := h
          }

          running = false
        })
      }
    })
  }
}