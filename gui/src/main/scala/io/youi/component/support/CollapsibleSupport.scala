package io.youi.component.support

import io.youi.component.types.Display
import io.youi.component.Component
import io.youi.easing.Easing
import io.youi.task._
import reactify.Var

import scala.concurrent.Future
import scala.concurrent.duration._
import scribe.Execution.global

trait CollapsibleSupport {
  this: Component with SizeSupport =>

  private var future: Future[Unit] = Future.successful(())

  val easing: Var[Easing] = Var(Easing.exponentialOut)
  val speed: Var[FiniteDuration] = Var(300.millis)
  val collapsed: Var[Boolean] = Var(false)

  protected def expandedHeight: Double

  size.height := expandedHeight

  collapsed.attach {
    case true => collapse()
    case false => expand()
  }

  private def expand(): Future[Unit] = {
    future = future.flatMap { _ =>
      sequential(
        size.height @= 0.0,
        display @= Display.Block,
        size.height.to(expandedHeight).in(speed).easing(easing)
      ).start().future.map(_ => ())
    }
    future
  }

  private def collapse(): Future[Unit] = {
    future = future.flatMap { _ =>
      sequential(
        size.height.to(0.0).in(speed).easing(easing),
        display @= Display.None
      ).start().future.map(_ => ())
    }
    future
  }
}
