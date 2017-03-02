package io.youi.workflow

import io.youi.easing.Easing

import scala.concurrent.duration.FiniteDuration

case class PartialAnimate(get: () => Double,
                          apply: Double => Unit,
                          destination: () => Double) {
  def in(duration: FiniteDuration) = AnimateIn(get, apply, destination, duration, Easing.linear)

  def by(stepBy: Double): Temporal = new Temporal {
    override def update(delta: Double, elapsed: Double): Conclusion = {
      val current = get()
      val end = destination()
      val step = delta * stepBy
      if (current < end) {
        val value = current + step
        if (value > end) {
          apply(end)
          Conclusion.Finished
        } else {
          apply(value)
          Conclusion.Continue
        }
      } else {
        val value = current - step
        if (value < end) {
          apply(end)
          Conclusion.Finished
        } else {
          apply(value)
          Conclusion.Continue
        }
      }
    }
  }
}