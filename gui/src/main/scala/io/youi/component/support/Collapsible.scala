package io.youi.component.support

import cats.effect.IO
import io.youi.{Chained, Initializable, Plane, dom}
import io.youi.component.Component
import io.youi.component.feature.{HeightFeature, WidthFeature}
import io.youi.component.types.{Display, Overflow, SizeProperty, SizeType}
import io.youi.easing.Easing
import io.youi.task._
import reactify.{Val, Var}
import scala.concurrent.duration._

abstract class Collapsible extends Component(dom.create.div) with InternalContainerSupport[Component] with SizeSupport with OverflowSupport with Initializable {
  protected def container: Component

  protected lazy val chained = Chained(1)

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

  private def expand(): IO[Unit] = {
    init()
    if (animate) {
      chained {
        sequential(
          IO(prop @= 0.0),
          IO(display @= Display.Block),
          prop.to(expanded).in(speed).easing(easing),
//          prop := expanded
          IO(prop.`type` @= SizeType.Auto)
        ).start().map(_ => ())
      }
    } else {
      chained {
        IO {
          prop.set(0.0, SizeType.Auto)
          display @= Display.Block
        }
      }
    }
  }

  private def collapse(): IO[Unit] = {
    init()
    if (animate) {
      chained {
        sequential(
          IO(prop.`type` @= SizeType.Pixel),
          IO(prop @= expanded),
          prop.to(0.0).in(speed).easing(easing),
          IO(display @= Display.None)
        ).start().map(_ => ())
      }
    } else {
      chained {
        IO {
          prop.set(0.0, SizeType.Pixel)
          display @= Display.None
        }
      }
    }
  }
}