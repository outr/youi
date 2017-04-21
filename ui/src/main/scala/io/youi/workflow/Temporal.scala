package io.youi.workflow

import io.youi.{AnimationFrame, Updates}
import reactify._

import scala.concurrent.{Future, Promise}

trait Temporal extends Task {
  protected def updates: Updates = AnimationFrame

  def run(): Future[Unit] = {
    val promise = Promise[Unit]
    var listener: Listener[Double] = null
    var elapsed = 0.0
    var step = 0.0
    listener = (delta: Double) => {
      elapsed += delta
      step += delta
      if (step >= stepSize) {
        update(delta, elapsed) match {
          case Conclusion.Continue => // Keep going
          case Conclusion.Finished => {
            updates.delta.detach(listener)
            promise.success(())
          }
        }
        step = 0.0
      }
    }
    updates.delta.observe(listener)
    promise.future
  }

  def stepSize: Double = 0.0

  def update(delta: Double, elapsed: Double): Conclusion
}