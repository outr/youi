package io.youi.task

class Action(action: () => rapid.Task[Unit]) extends Task {
  private var running = false
  private var finished = false

  override def update(delta: Double, reset: Boolean): Conclusion = {
    if (reset) {
      finished = false
      running = false
    }
    if (!finished && !running) {
      running = true
      action().map { _ =>
        finished = true
        running = false
      }.startUnit()
      Conclusion.Continue
    } else if (!finished) {
      Conclusion.Continue
    } else {
      Conclusion.Finished
    }
  }
}

object Action {
  def apply(f: => rapid.Task[Unit]): Action = new Action(() => f)
}
