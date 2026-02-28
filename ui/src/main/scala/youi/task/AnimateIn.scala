package youi.task

import youi.easing.Easing

import scala.concurrent.duration.FiniteDuration

case class AnimateIn(get: () => Double,
                     apply: Double => Unit,
                     destination: () => Double,
                     duration: () => FiniteDuration,
                     easing: Easing) extends DurationTask {
  private var initialPosition: Double = 0.0

  def easing(easing: Easing): AnimateIn = copy(easing = easing)

  override def time: FiniteDuration = duration()

  override def act(delta: Double, elapsed: Double, progress: Double, reset: Boolean): Unit = {
    if (reset) {
      initialPosition = get()
    }
    val endPosition = destination()
    val length = endPosition - initialPosition
    val eased = easing.calculate(progress)
    val adjust = length * eased
    val value = initialPosition + adjust
    apply(value)
  }
}