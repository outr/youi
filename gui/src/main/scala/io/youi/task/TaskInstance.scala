package io.youi.task

import cats.effect.unsafe.implicits.global
import cats.effect.{Deferred, IO}
import io.youi.Updates
import reactify.reaction.Reaction

case class TaskInstance(task: Task, updates: Updates, deferred: Option[Deferred[IO, Double]]) {
  private var reaction: Reaction[Double] = _
  private var first = true
  private var elapsed: Double = 0.0
  private var paused: Boolean = false
  private var step: Double = 0.0

  reaction = Reaction[Double] { delta =>
    val updateTask = updates match {
      case ts: TaskSupport => ts.updateTasks()
      case _ => true
    }

    if (!paused && updateTask) {
      elapsed += delta
      if (step >= task.stepSize) {
        step = 0.0
        task.update(delta, first) match {
          case Conclusion.Continue => // Keep going
          case Conclusion.Finished =>
            updates.delta.reactions -= reaction
            deferred.foreach(_.complete(elapsed).unsafeRunAndForget())
        }
        first = false
      }
    }
  }

  def start(): Unit = {
    updates.delta.reactions += reaction
  }

  def pause(): Unit = paused = true
  def play(): Unit = paused = false

  def cancel(): Unit = {
    updates.delta.reactions -= reaction
  }
}