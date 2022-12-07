package io.youi.task

import cats.effect.IO
import cats.effect.unsafe.implicits.global

class Action(action: () => IO[Unit]) extends Task {
  private var running = false
  private var finished = false

  override def update(delta: Double, reset: Boolean): Conclusion = {
    if (!finished && !running) {
      running = true
      action().map { _ =>
        finished = true
        running = false
      }.unsafeRunAndForget()
      Conclusion.Continue
    } else if (!finished) {
      Conclusion.Continue
    } else {
      Conclusion.Finished
    }
  }
}

object Action {
  def apply(f: => IO[Unit]): Action = new Action(() => f)
}