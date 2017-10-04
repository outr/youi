package io.youi

import io.youi.util.CanvasPool
import org.scalajs.dom.window
import reactify._

import scala.scalajs.js

object ui {
  lazy val devicePixelRatio: Double = window.devicePixelRatio
  lazy val backingStoreRatio: Double = CanvasPool.withCanvas(1.0, 1.0) { canvas =>
    val ctx = canvas.context.asInstanceOf[js.Dynamic]
    val webkit = ctx.webkitBackingStorePixelRatio.asInstanceOf[Option[Double]]
    val moz = ctx.mozBackingStorePixelRatio.asInstanceOf[Option[Double]]
    val ms = ctx.msBackingStorePixelRatio.asInstanceOf[Option[Double]]
    val o = ctx.oBackingStorePixelRatio.asInstanceOf[Option[Double]]
    val default = ctx.backingStorePixelRatio.asInstanceOf[Option[Double]]
    webkit.orElse(moz).orElse(ms).orElse(o).orElse(default).getOrElse(1.0)
  }
  lazy val ratio: Var[Double] = Var(devicePixelRatio / backingStoreRatio)
  lazy val width: Val[Double] = Val(Display.width * ratio)
  lazy val height: Val[Double] = Val(Display.height * ratio)

  lazy val center: Val[Double] = Val(width / 2.0)
  lazy val middle: Val[Double] = Val(height / 2.0)
}