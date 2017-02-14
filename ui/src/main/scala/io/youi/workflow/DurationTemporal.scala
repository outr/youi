package io.youi.workflow

trait DurationTemporal extends Temporal {
  def time: FiniteDuration

  def act(delta: Double, elapsed: Double, progress: Double): Unit

  override final def update(delta: Double, elapsed: Double): Conclusion = {
    val duration = time.toMillis / 1000.0
    val progress = math.min(elapsed / duration, 1.0)
    act(delta, elapsed, progress)
    if (elapsed >= duration) {
      Conclusion.Finished
    } else {
      Conclusion.Continue
    }
  }
}
