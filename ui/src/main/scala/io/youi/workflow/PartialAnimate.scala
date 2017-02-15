package io.youi.workflow

import com.outr.reactify.StateChannel
import io.youi.easing.Easing

import scala.concurrent.duration.FiniteDuration

case class PartialAnimate(state: StateChannel[Double], destination: () => Double) {
  def in(duration: FiniteDuration) = AnimateIn(state, destination, duration, Easing.linear)

  def by(stepBy: Double): Temporal = new Temporal {
    override def update(delta: Double, elapsed: Double): Conclusion = {
      val current = state()
      val end = destination()
      val step = delta * stepBy
      if (current < end) {
        val value = current + step
        if (value > end) {
          state := end
          Conclusion.Finished
        } else {
          state := value
          Conclusion.Continue
        }
      } else {
        val value = current - step
        if (value < end) {
          state := end
          Conclusion.Finished
        } else {
          state := value
          Conclusion.Continue
        }
      }
    }
  }
}