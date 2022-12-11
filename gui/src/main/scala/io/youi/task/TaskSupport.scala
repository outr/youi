package io.youi.task

import cats.effect.IO
import io.youi.Updates

trait TaskSupport extends Updates {
  protected def createInstance(task: Task): IO[TaskInstance] = TaskInstance(task, this)

  def start(task: Task): IO[TaskInstance] = createInstance(task).map { instance =>
    instance.start()
    instance
  }

  def updateTasks(): Boolean = true
}
