package io.youi.maintenance

import io.youi.util.Time
import scribe.Execution.global
import perfolation._

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object Maintenance {
  def schedule(task: MaintenanceTask,
               delayOverride: Option[FiniteDuration] = None,
               firstRun: Boolean = true): Future[TaskStatus] = {
    val taskDelay = if (firstRun) {
      task.firstRunDelay
    } else {
      task.delay
    }
    if (task.logScheduling) {
      scribe.info(s"${task.name} scheduled to run next at ${(System.currentTimeMillis() + taskDelay.toMillis).t.c}")
    }

    val result = Time.delay(taskDelay).flatMap { _ =>
      val time = System.currentTimeMillis()
      task.status = MaintenanceStatus.Running(time)
      val future = task.run()
      future.onComplete { result =>
        val now = System.currentTimeMillis()
        val elapsed = now - time
        task.lastRunTime = Some(elapsed)
        task.longestRunTime = Some(math.max(task.longestRunTime.getOrElse(0L), elapsed))
        task.timesRun += 1
        task.lastRun = Some(now)
        val status = result match {
          case Success(s) => {
            task.lastStatus = Some(s)
            task.status = MaintenanceStatus.Finished
            s
          }
          case Failure(throwable) => {
            val s = task.statusOnFailure
            task.lastStatus = Some(s)
            task.status = MaintenanceStatus.Failure
            scribe.error(new RuntimeException(s"Error thrown in ${task.name} during maintenance", throwable))
            s
          }
        }
        status match {
          case TaskStatus.Repeat => schedule(task, None, firstRun = false)
          case TaskStatus.RepeatNow => schedule(task, Some(0.seconds), firstRun = false)
          case TaskStatus.RepeatIn(t) => schedule(task, Some(t), firstRun = false)
          case TaskStatus.Stop => // Stop
        }
      }
      future
    }
    task.status = MaintenanceStatus.Scheduled
    result
  }

  def apply(delay: FiniteDuration,
            repeat: Boolean,
            statusOnFailure: TaskStatus = TaskStatus.Stop)
           (f: => Future[Unit]): Future[TaskStatus] = {
    val d = delay
    val sof = statusOnFailure
    val task = new MaintenanceTask {
      override def run(): Future[TaskStatus] = f.map(_ => if (repeat) TaskStatus.Repeat else TaskStatus.Stop)
      override def delay: FiniteDuration = d
      override def statusOnFailure: TaskStatus = sof
    }
    schedule(task, None)
  }
}