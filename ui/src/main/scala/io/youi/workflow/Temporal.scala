package io.youi.workflow

import io.youi.AnimationFrame

import scala.concurrent.{Future, Promise}

trait Temporal extends Task {
  def start(): Future[Unit] = {
    val promise = Promise[Unit]
    var f: Double => Unit = null
    var elapsed = 0.0
    var step = 0.0
    f = (delta: Double) => {
      elapsed += delta
      step += delta
      if (step >= stepSize) {
        update(delta, elapsed) match {
          case Conclusion.Continue => // Keep going
          case Conclusion.Finished => AnimationFrame.delta.detach(f)
        }
        step = 0.0
      }
    }
    AnimationFrame.delta.attach(f)
    promise.future
  }

  def stepSize: Double = 0.0

  def update(delta: Double, elapsed: Double): Conclusion
}