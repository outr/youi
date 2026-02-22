package io.youi.task

import rapid.task.Completable
import io.youi.Updates

trait TaskSupport extends Updates {
  protected def createInstance(task: Task, deferred: Option[Completable[Double]]): TaskInstance =
    TaskInstance(task, this, deferred)

  def start(task: Task, deferred: Option[Completable[Double]]): TaskInstance = {
    val instance = createInstance(task, deferred)
    instance.start()
    instance
  }

  def updateTasks(): Boolean = true
}
