package io.youi.task

import cats.effect.unsafe.implicits.global
import cats.effect.{Deferred, IO}
import io.youi.Updates
import reactify.reaction.Reaction

case class TaskInstance(task: Task, updates: Updates, deferred: Deferred[IO, Either[Throwable, Double]]) {
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
            deferred.complete(Right(elapsed)).unsafeRunAndForget()
        }
        first = false
      }
    }
  }

  def start(): IO[Double] = IO {
    updates.delta.reactions += reaction
  }.flatMap { _ =>
    deferred.get.map {
      case Left(t) => throw t
      case Right(elapsed) => elapsed
    }
  }

  def pause(): Unit = paused = true
  def play(): Unit = paused = false

  def cancel(): Unit = IO {
    updates.delta.reactions -= reaction
  }.flatMap { _ =>
    deferred.complete(Left(new TaskCancelledException))
  }
}

object TaskInstance {
  def apply(task: Task, updated: Updates): IO[TaskInstance] = Deferred[IO, Either[Throwable, Double]].map { d =>
    TaskInstance(task, updated, d)
  }
}