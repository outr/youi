package io.youi.task

import cats.effect.IO
import io.youi.ui

trait Task {
  def update(delta: Double, reset: Boolean): Conclusion

  def stepSize: Double = 0.0

  def andThen(that: Task): Task = new Sequential(List(this, that))

  def start(taskSupport: TaskSupport = ui): IO[TaskInstance] = taskSupport.start(this)
}

object Task {
  lazy val None: Task = new Task {
    override def update(delta: Double, reset: Boolean): Conclusion = Conclusion.Finished
  }
}