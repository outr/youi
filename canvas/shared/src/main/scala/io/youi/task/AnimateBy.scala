package io.youi.task

case class AnimateBy(stepBy: Double,
                     get: () => Double,
                     apply: Double => Unit,
                     destination: () => Double) extends Task {
  override def update(delta: Double, reset: Boolean): Conclusion = {
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
