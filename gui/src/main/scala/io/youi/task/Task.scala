package io.youi.task

import cats.effect.{Deferred, IO}
import io.youi.ui

trait Task {
  def update(delta: Double, reset: Boolean): Conclusion

  def stepSize: Double = 0.0

  def andThen(that: Task): Task = new Sequential(List(this, that))

  def start(taskSupport: TaskSupport = ui, deferred: Option[Deferred[IO, Double]] = None): TaskInstance =
    taskSupport.start(this, deferred)

  def startDeferred(taskSupport: TaskSupport = ui): IO[Double] = Deferred[IO, Double].flatMap { deferred =>
    start(taskSupport, Some(deferred))
    deferred.get
  }
}

object Task {
  lazy val None: Task = new Task {
    override def update(delta: Double, reset: Boolean): Conclusion = Conclusion.Finished
  }
}