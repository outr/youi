package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.{PositionType, Prop, SizeProperty}
import reactify._

import scala.util.Try

class PositionFeature(override val component: Component) extends Feature {
  lazy val x: SizeProperty = new SizeProperty(component.element.style.left, component.element.style.left_=)
  lazy val y: SizeProperty = new SizeProperty(component.element.style.top, component.element.style.top_=)
  lazy val z: Prop[Int] = new Prop[Int](Try(component.element.style.zIndex.toInt).getOrElse(0), i => component.element.style.zIndex = i.toString)

  val `type`: Prop[PositionType] = {
    val p = new Prop[PositionType](
      getter = PositionType(component.element.style.position),
      setter = pt => {
        component.element.style.position = pt.name
        component.element.style.left = x.toString
        component.element.style.top = y.toString
      }
    )
    x.and(y).on {
      if (p() == PositionType.Static) {
        p @= PositionType.Absolute
      }
    }
    p
  }

  lazy val left: Var[Double] = x
  lazy val center: Dep[Double, Double] = Component.width(component) match {
    case Some(w) => Dep(left)(_ + (w / 2.0), _ - (w / 2.0))
    case None => Dep(left)(identity, identity)
  }
  lazy val right: Dep[Double, Double] = Component.width(component) match {
    case Some(w) => Dep(left)(_ + w, _ - w)
    case None => Dep(left)(identity, identity)
  }

  lazy val top: Var[Double] = y
  lazy val middle: Dep[Double, Double] = Component.height(component) match {
    case Some(h) => Dep(top)(_ + (h / 2.0), _ - (h / 2.0))
    case None => Dep(top)(identity, identity)
  }
  lazy val bottom: Dep[Double, Double] = Component.height(component) match {
    case Some(h) => Dep(top)(_ + h, _ - h)
    case None => Dep(top)(identity, identity)
  }

  lazy val depth: Var[Int] = z
}