package io.youi.workflow

import io.youi.easing.Easing

import scala.concurrent.duration.FiniteDuration

case class AnimateIn(get: () => Double,
                     apply: Double => Unit,
                     destination: () => Double,
                     duration: FiniteDuration,
                     easing: Easing) extends DurationTemporal {
  private var initialPosition: Double = 0.0

  def easing(easing: Easing): AnimateIn = copy(easing = easing)

  override protected def starting(): Unit = {
    super.starting()

    initialPosition = get()
  }

  override def act(delta: Double, elapsed: Double, progress: Double): Unit = {
    val endPosition = destination()
    val length = endPosition - initialPosition
    val eased = easing.calculate(progress)
    val adjust = length * eased
    val value = initialPosition + adjust
    apply(value)
  }

  override def time: FiniteDuration = duration
}
