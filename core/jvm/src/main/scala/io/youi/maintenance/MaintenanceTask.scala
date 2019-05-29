package io.youi.maintenance

import java.util.{Calendar, TimeZone}

import reactify.Var

import scala.concurrent.Future
import scala.concurrent.duration._

trait MaintenanceTask {
  val name: String = getClass.getSimpleName

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

  def at(hour: Int,
         minute: Int = 0,
         second: Int = 0,
         millisecond: Int = 0,
         rollToNextDay: Boolean = true,
         timeZone: TimeZone = TimeZone.getDefault): FiniteDuration = {
    val c = Calendar.getInstance(timeZone)
    c.set(Calendar.HOUR_OF_DAY, hour)
    c.set(Calendar.MINUTE, minute)
    c.set(Calendar.SECOND, second)
    c.set(Calendar.MILLISECOND, millisecond)
    val l = c.getTimeInMillis - System.currentTimeMillis()
    if (l <= 0 && rollToNextDay) {
      (l + 24.hours.toMillis).millis
    } else {
      l.millis
    }
  }
}