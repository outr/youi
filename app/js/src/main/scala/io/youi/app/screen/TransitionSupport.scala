package io.youi.app.screen

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait TransitionSupport {
  this: ScreenManager =>

  private[screen] var future: Future[Unit] = Future.successful(())

  def transitionFuture: Future[Unit] = future

  private var transitionActive: Boolean = false

  working.attach { screens =>
    if (screens.isEmpty && transitionActive) {
      transitionActive = false
      future = future.flatMap(_ => transitionEnd())
    } else if (screens.nonEmpty && !transitionActive && future.isCompleted) {
      transitionActive = true
      future = future.flatMap(_ => transitionBegin())
    }
  }

  def transitionBegin(): Future[Unit]

  def transitionEnd(): Future[Unit]
}