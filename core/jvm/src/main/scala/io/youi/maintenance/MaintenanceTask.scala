package io.youi.maintenance

import reactify.Var

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration

trait MaintenanceTask {
  var lastStatus: Option[TaskStatus] = None
  var lastRunTime: Option[Long] = None
  var longestRunTime: Option[Long] = None
  var lastRun: Option[Long] = None
  var status: MaintenanceStatus = MaintenanceStatus.Init
  var timesRun: Int = 0
  var failures: Int = 0
  val message: Var[Option[String]] = Var(None)

  message.attach { value =>
    value.foreach(msg => scribe.info(s"${getClass.getSimpleName}: $msg"))
  }

  def firstRunDelay: FiniteDuration = delay
  def delay: FiniteDuration
  def statusOnFailure: TaskStatus = TaskStatus.Stop
  def run(): Future[TaskStatus]
}