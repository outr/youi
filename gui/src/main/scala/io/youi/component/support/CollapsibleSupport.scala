package io.youi.component.support

import io.youi.Plane
import io.youi.component.Component
import io.youi.component.types.{Display, SizeProperty, SizeType}
import io.youi.easing.Easing
import io.youi.task._
import reactify.Var
import scribe.Execution.global

import scala.concurrent.Future
import scala.concurrent.duration._

trait CollapsibleSupport {
  this: Component with MaxSizeSupport =>

  protected var future: Future[Unit] = Future.successful(())

  val easing: Var[Easing] = Var(Easing.exponentialOut)
  val speed: Var[FiniteDuration] = Var(300.millis)
  val animate: Var[Boolean] = Var(true)
  val collapsed: Var[Boolean] = Var(true)

  protected def direction: Plane
  protected def expanded: Double = direction match {
    case Plane.Horizontal => element.scrollWidth
    case Plane.Vertical => element.scrollHeight
  }

  private val prop: SizeProperty = direction match {
    case Plane.Horizontal => maxSize.width
    case Plane.Vertical => maxSize.height
  }

  element.style.overflow = "hidden"

  prop := 0.0

  collapsed.attachAndFire {
    case true => collapse()
    case false => expand()
  }

  private def expand(): Future[Unit] = if (animate) {
    future = future.flatMap { _ =>
      sequential(
        prop @= 0.0,
        display @= Display.Block,
        prop.to(expanded).in(speed).easing(easing),
        prop.`type` @= SizeType.Auto
      ).start().future.map(_ => ())
    }
    future
  } else {
    future = future.map { _ =>
      prop.set(0.0, SizeType.Auto)
      display @= Display.Block
    }
    future
  }

  private def collapse(): Future[Unit] = if (animate) {
    future = future.flatMap { _ =>
      sequential(
        prop.`type` @= SizeType.Pixel,
        prop @= expanded,
        prop.to(0.0).in(speed).easing(easing),
        display @= Display.None
      ).start().future.map(_ => ())
    }
    future
  } else {
    future = future.map { _ =>
      prop.set(0.0, SizeType.Pixel)
      display @= Display.None
    }
    future
  }
}