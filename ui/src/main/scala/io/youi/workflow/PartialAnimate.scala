package io.youi.workflow

import com.outr.reactify.StateChannel

import scala.concurrent.duration.FiniteDuration

case class PartialAnimate(state: StateChannel[Double], destination: () => Double) {
  def in(d: FiniteDuration): DurationTemporal = new DurationTemporal {
    private var initialPosition: Double = 0.0

    override protected def starting(): Unit = {
      super.starting()

      initialPosition = state()
    }

    override def act(delta: Double, elapsed: Double, progress: Double): Unit = {
      val endPosition = destination()
      val length = endPosition - initialPosition
      val adjust = length * progress
      val value = initialPosition + (length * progress)
      state := value
    }

    override def time: FiniteDuration = d
  }

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
