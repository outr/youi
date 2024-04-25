package io.youi.task

import cats.effect.{Deferred, IO}
import io.youi.Updates

trait TaskSupport extends Updates {
  protected def createInstance(task: Task, deferred: Option[Deferred[IO, Double]]): TaskInstance =
    TaskInstance(task, this, deferred)

  def start(task: Task, deferred: Option[Deferred[IO, Double]]): TaskInstance = {
    val instance = createInstance(task, deferred)
    instance.start()
    instance
  }

  def updateTasks(): Boolean = true
}
