package io.youi

import reactify._
import org.scalajs.dom._

object AnimationFrame {
  private var lastUpdate: Double = 0.0
  private val updateFunction: Double => Unit = update
  private val _delta = Var(0.0)
  private val _timeStamp = Var(0.0)

  val delta: Val[Double] = Val(_delta)
  val timeStamp: Val[Double] = Val(_timeStamp)

  window.requestAnimationFrame(updateFunction)

  def nextFrame(f: => Unit): Unit = delta.once(_ => f)

  private def update(highResTimeStamp: Double): Unit = {
    val delta = if (lastUpdate == 0.0) {
      0.0
    } else {
      (highResTimeStamp - lastUpdate) / 1000.0
    }
    try {
      _delta := delta
      _timeStamp := highResTimeStamp
    } finally {
      lastUpdate = highResTimeStamp
      window.requestAnimationFrame(updateFunction)
    }
  }
}
