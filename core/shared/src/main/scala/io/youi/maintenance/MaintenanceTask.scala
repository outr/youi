package io.youi.maintenance

import cats.effect.IO

import scala.concurrent.duration.FiniteDuration

trait MaintenanceTask {
  def name: String = getClass.getSimpleName.replace("$", "")

  def nextRun: FiniteDuration

  def initialDelay: FiniteDuration = nextRun

  def onFail: TaskResult = TaskResult.Continue

  def apply(status: TaskStatus): IO[TaskResult]

  def schedule(): MaintenanceTaskInstance = Maintenance.schedule(
    name = name,
    schedule = nextRun,
    initialDelay = Some(initialDelay),
    onFail = onFail
  )(apply)
}