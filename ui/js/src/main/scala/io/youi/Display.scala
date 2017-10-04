package io.youi

import io.youi.event.HTMLEvents
import org.scalajs.dom.{Event, document, window}
import reactify._

object Display {
  private lazy val _width: Var[Double] = Var[Double](window.innerWidth)
  private lazy val _height: Var[Double] = Var[Double](window.innerHeight)

  lazy val event = new HTMLEvents(document.body)

  def width: Val[Double] = _width
  def height: Val[Double] = _height

  lazy val center: Val[Double] = Val(width / 2.0)
  lazy val middle: Val[Double] = Val(height / 2.0)

  window.addEventListener("resize", (_: Event) => {
    _width := window.innerWidth
    _height := window.innerHeight
  })
}