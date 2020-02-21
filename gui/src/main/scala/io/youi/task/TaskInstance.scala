package io.youi.task

import io.youi.Updates
import reactify._
import reactify.reaction.Reaction

import scala.concurrent.{Future, Promise}

class TaskInstance(task: Task, updates: Updates) {
  private val promise = Promise[Double]
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
          case Conclusion.Finished => {
            updates.delta.reactions -= reaction
            promise.success(elapsed)
          }
        }
        first = false
      }
    }
  }

  val future: Future[Double] = promise.future

  def start(): Future[Double] = {
    updates.delta.reactions += reaction
    future
  }

  def pause(): Unit = paused = true
  def play(): Unit = paused = false

  def cancel(): Unit = promise.failure(new TaskCancelledException)
}