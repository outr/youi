package io.youi

import io.youi.event.HTMLEvents
import io.youi.util.CanvasPool
import org.scalajs.dom.{Event, document, window}
import reactify._

import scala.concurrent.duration._
import scala.scalajs.js

object ui {
  private lazy val _width: Var[Double] = Var[Double](window.innerWidth)
  private lazy val _height: Var[Double] = Var[Double](window.innerHeight)

  def devicePixelRatio: Double = window.devicePixelRatio
  def backingStoreRatio: Double = CanvasPool.withCanvas(1.0, 1.0) { canvas =>
    def opt(d: js.Dynamic): Option[Double] = d.asInstanceOf[js.UndefOr[Double]].toOption
    val ctx = canvas.context.asInstanceOf[js.Dynamic]
    val webkit = opt(ctx.webkitBackingStorePixelRatio)
    val moz = opt(ctx.mozBackingStorePixelRatio)
    val ms = opt(ctx.msBackingStorePixelRatio)
    val o = opt(ctx.oBackingStorePixelRatio)
    val default = opt(ctx.backingStorePixelRatio)
    webkit.orElse(moz).orElse(ms).orElse(o).orElse(default).getOrElse(1.0)
  }
  lazy val displayRatio: Val[Double] = Var(devicePixelRatio / backingStoreRatio)
  lazy val ratio: Var[Double] = Var(displayRatio)

  lazy val event = new HTMLEvents(document.body)

  def width: Val[Double] = _width
  def height: Val[Double] = _height

  lazy val center: Val[Double] = Val(width / 2.0)
  lazy val middle: Val[Double] = Val(height / 2.0)

  window.addEventListener("resize", (_: Event) => {
    _width := window.innerWidth
    _height := window.innerHeight
  })

  AnimationFrame.every(1.second) {
    val r = devicePixelRatio / backingStoreRatio
    if (r != displayRatio()) {
      displayRatio.asInstanceOf[Var[Double]] := r
    }
  }
}