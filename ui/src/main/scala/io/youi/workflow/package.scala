package io.youi

import com.outr.reactify.StateChannel

import scala.concurrent.Future
import scala.concurrent.duration.FiniteDuration

package object workflow {
  implicit class StateChannelWorkflow(state: StateChannel[Double]) {
    def to(destination: => Double): PartialAnimate = PartialAnimate(state, () => destination)
  }

  def parallel(tasks: Task*): Parallel = new Parallel(tasks.toList)
  def sequential(tasks: Task*): Sequential = new Sequential(tasks.toList)
  def sleep(duration: FiniteDuration): Sleep = new Sleep(duration)
  def action(f: => Future[Unit]): Action = Action(f)
  def synchronous(f: => Unit): Action = Action {
    f
    Future.successful(())
  }
}