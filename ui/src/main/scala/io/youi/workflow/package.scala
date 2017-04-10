package io.youi

import reactify._

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration
import scala.language.implicitConversions

package object workflow {
  implicit def future2Task(future: => Future[Unit]): Task = new Task {
    override protected def run(): Future[Unit] = future
  }
  implicit def f2Task(f: => Unit): Task = new Task {
    override protected def run(): Future[Unit] = {
      f
      Future.successful(())
    }
  }

  implicit class StateChannelWorkflow(state: StateChannel[Double]) {
    def to(destination: => Double): PartialAnimate = PartialAnimate(
      get = () => state(),
      apply = (d: Double) => state := d,
      destination = () => destination
    )
  }

  def parallel(tasks: Task*): Parallel = new Parallel(tasks.toList)
  def sequential(tasks: Task*): Sequential = new Sequential(tasks.toList)
  def sleep(duration: FiniteDuration): Sleep = new Sleep(duration)
  def asynchronous(f: => Future[Unit]): Action = Action(f)
  def synchronous(f: => Unit): Action = Action {
    f
    Future.successful(())
  }
  def repeat(task: Task, times: Int = 1): Repeat = new Repeat(task, times)
  def forever(task: Task): Repeat = new Repeat(task, Int.MaxValue)
}