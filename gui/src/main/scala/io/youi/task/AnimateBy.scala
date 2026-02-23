package io.youi.task

case class AnimateBy(get: () => Double,
                     apply: Double => Unit,
                     destination: () => Double,
                     speed: Double) extends Task {
  override def update(delta: Double, reset: Boolean): Conclusion = {
    val current = get()
    val target = destination()
    val diff = target - current
    if (math.abs(diff) < 0.001) {
      apply(target)
      Conclusion.Finished
    } else {
      val step = speed * delta
      if (math.abs(diff) <= step) {
        apply(target)
        Conclusion.Finished
      } else {
        apply(current + math.signum(diff) * step)
        Conclusion.Continue
      }
    }
  }
}
