package io.youi.component.feature

import io.youi.{AnimationFrame, ui}
import io.youi.component.Component
import io.youi.component.types.Prop
import io.youi.easing.Easing
import io.youi.task.{TaskInstance, TaskSupport}
import org.scalajs.dom.Event
import reactify.Var
import io.youi.task._

import scala.concurrent.duration._

class ScrollFeature(component: Component) extends Feature {
  override protected def parent: FeatureParent = component

  lazy val x: Prop[Double] = new Prop[Double](component.element.scrollLeft, component.element.scrollLeft_=)
  lazy val y: Prop[Double] = new Prop[Double](component.element.scrollTop, component.element.scrollTop_=)
  /**
    * If enabled, uses animation frame to smooth out events to avoid overloading with events. Defaults to true.
    */
  val optimized: Var[Boolean] = Var(true)

  private var ticking = false

  component.element.addEventListener("scroll", (_: Event) => {
    if (optimized()) {
      if (!ticking) {
        AnimationFrame.nextFrame {
          validate()
          ticking = false
        }
        ticking = true
      }
    } else {
      validate()
    }
  })

  private def validate(): Unit = {
    if (x() != component.element.scrollLeft) {
      x @= component.element.scrollLeft
    }
    if (y() != component.element.scrollTop) {
      y @= component.element.scrollTop
    }
  }

  def to(child: Component,
         in: FiniteDuration = 250.millis,
         easing: Easing = Easing.linear,
         vertical: Boolean = true,
         horizontal: Boolean = false,
         alignBottom: Boolean = false,
         alignRight: Boolean = false,
         parent: TaskSupport = ui): TaskInstance = {
    val parentRect = component.getBoundingClientRect()
    val childRect = child.getBoundingClientRect()
    val v = if (vertical) {
      val top = child.element.offsetTop - component.element.offsetTop
      val destination = if (alignBottom) {
        top - parentRect.height + childRect.height
      } else {
        top
      }
      Some(y to destination in in easing easing)
    } else {
      None
    }
    val h = if (horizontal) {
      val left = child.element.offsetLeft - component.element.offsetLeft
      val destination = if (alignRight) {
        left - parentRect.width + childRect.width
      } else {
        left
      }
      Some(x to destination in in easing easing)
    } else {
      None
    }
    parallel(List(v, h).flatten: _*).start(parent)
  }
}