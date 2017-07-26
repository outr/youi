package io.youi

import io.youi.task.TaskSupport
import org.scalajs.dom._
import reactify._

object AnimationFrame extends TaskSupport {
  private var lastUpdate: Double = 0.0

  val timeStamp: Val[Double] = Var(0.0)

  private val updateFunction: Double => Unit = (highResTimeStamp: Double) => {
    val delta = if (lastUpdate == 0.0) {
      0.0
    } else {
      (highResTimeStamp - lastUpdate) / 1000.0
    }
    try {
      timeStamp.asInstanceOf[Var[Double]] := highResTimeStamp
      update(delta)
    } finally {
      lastUpdate = highResTimeStamp
      window.requestAnimationFrame(updateFunction)
    }
  }

  window.requestAnimationFrame(updateFunction)
}