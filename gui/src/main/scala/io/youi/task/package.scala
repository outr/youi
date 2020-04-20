package io.youi

import reactify.{Mutable, Stateful}

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration
import scala.language.implicitConversions

package object task {
  implicit def future2Task[R](future: => Future[R]): Task = FutureTask[R](future)
  implicit def f2Task(f: => Unit): Task = Action(f)

  implicit class StateChannelWorkflowDouble(state: Stateful[Double] with Mutable[Double]) {
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