package youi.task

import scala.concurrent.duration.FiniteDuration

trait DurationTask extends Task {
  private var elapsed: Double = 0.0

  def time: FiniteDuration
  def act(delta: Double, elapsed: Double, progress: Double, reset: Boolean): Unit

  override def update(delta: Double, reset: Boolean): Conclusion = {
    if (reset) {
      elapsed = 0.0
    }
    elapsed += delta
    val duration = time.toMillis / 1000.0
    val progress = math.min(elapsed / duration, 1.0)
    act(delta, elapsed, progress, reset)
    if (elapsed >= duration) {
      Conclusion.Finished
    } else {
      Conclusion.Continue
    }
  }
}