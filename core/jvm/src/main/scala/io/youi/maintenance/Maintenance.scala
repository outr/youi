package io.youi.maintenance

import java.util.concurrent.atomic.AtomicBoolean

import akka.actor.{ActorSystem, Cancellable, Scheduler}
import scribe.Execution.global

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object Maintenance {
  private val disposed = new AtomicBoolean(false)
  private lazy val system: ActorSystem = ActorSystem("maintenance")
  private lazy val scheduler: Scheduler = system.scheduler

  def schedule(task: MaintenanceTask,
               delayOverride: Option[FiniteDuration] = None,
               firstRun: Boolean = true): Option[Cancellable] = if (!disposed.get()) {
    val taskDelay = if (firstRun) {
      task.firstRunDelay
    } else {
      task.delay
    }
    val scheduled = Some(scheduler.scheduleOnce(delayOverride.getOrElse(taskDelay)) {
      if (!disposed.get()) {
        val time = System.currentTimeMillis()
        task.status = MaintenanceStatus.Running(time)
        task.run().onComplete { result =>
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
              scribe.error(new RuntimeException(s"Error thrown in $task during maintenance", throwable))
              s
            }
          }
          status match {
            case TaskStatus.Repeat => schedule(task, None, firstRun = false)
            case TaskStatus.RepeatNow => schedule(task, Some(0.seconds), firstRun = false)
            case TaskStatus.Stop => // Stop
          }
        }
      }
    })
    task.status = MaintenanceStatus.Scheduled
    scheduled
  } else {
    None
  }

  def apply(delay: FiniteDuration,
            repeat: Boolean,
            statusOnFailure: TaskStatus = TaskStatus.Stop)
           (f: => Future[Unit]): Option[Cancellable] = {
    val d = delay
    val sof = statusOnFailure
    val task = new MaintenanceTask {
      override def run(): Future[TaskStatus] = f.map(_ => if (repeat) TaskStatus.Repeat else TaskStatus.Stop)
      override def delay: FiniteDuration = d
      override def statusOnFailure: TaskStatus = sof
    }
    schedule(task, None)
  }

  def dispose(): Unit = if (disposed.compareAndSet(false, true)) {
    system.terminate()
    ()
  }
}