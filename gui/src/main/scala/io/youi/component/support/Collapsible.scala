package io.youi.component.support

import io.youi.{Initializable, Plane, dom}
import io.youi.component.Component
import io.youi.component.feature.{HeightFeature, WidthFeature}
import io.youi.component.types.{Display, Overflow, SizeProperty, SizeType}
import io.youi.easing.Easing
import io.youi.task._
import reactify.{Val, Var}
import scribe.Execution.global

import scala.concurrent.Future
import scala.concurrent.duration._

abstract class Collapsible extends Component(dom.create.div) with InternalContainerSupport[Component] with SizeSupport with OverflowSupport with Initializable {
  protected def container: Component

  protected var future: Future[Unit] = Future.successful(())

  protected def startCollapsed: Boolean = true

  val easing: Var[Easing] = Var(Easing.exponentialOut)
  val speed: Var[FiniteDuration] = Var(300.millis)
  val animate: Var[Boolean] = Var(true)
  val collapsed: Var[Boolean] = Var(startCollapsed)

  protected def direction: Plane
  protected lazy val width: Val[Double] = WidthFeature(container).getOrElse(throw new RuntimeException(s"No width support for $container"))
  protected lazy val height: Val[Double] = HeightFeature(container).getOrElse(throw new RuntimeException(s"No height support for $container"))
  protected def expanded: Double = direction match {
    case Plane.Horizontal => width
    case Plane.Vertical => height
  }

  private val prop: SizeProperty = direction match {
    case Plane.Horizontal => size.width
    case Plane.Vertical => size.height
  }

  override protected def initialize(): Unit = {
    element.style.overflow = "hidden"
    children += container

    overflow @= Overflow.Hidden

    prop := 0.0

    collapsed.attach {
      case true => collapse()
      case false => expand()
    }

    animate @= false
    try {
      if (collapsed) {
        collapse()
      } else {
        expand()
      }
    } finally {
      animate @= true
    }
  }

  private def expand(): Future[Unit] = {
    init()
    if (animate) {
      future = future.flatMap { _ =>
        sequential(
          prop @= 0.0,
          display @= Display.Block,
          prop.to(expanded).in(speed).easing(easing),
//          prop := expanded
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
  }

  private def collapse(): Future[Unit] = {
    init()
    if (animate) {
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
}