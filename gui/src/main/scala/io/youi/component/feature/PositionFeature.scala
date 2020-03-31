package io.youi.component.feature

import io.youi.component.Component
import io.youi.component.types.{PositionType, Prop, SizeProperty}
import reactify._

import scala.util.Try

class PositionFeature(component: Component) extends Feature(component) {
  lazy val x: SizeProperty = new SizeProperty(element.style.left, element.style.left_=)
  lazy val y: SizeProperty = new SizeProperty(element.style.top, element.style.top_=)
  lazy val z: Prop[Int] = new Prop[Int](Try(element.style.zIndex.toInt).getOrElse(0), i => element.style.zIndex = i.toString)

  val `type`: Prop[PositionType] = {
    val p = new Prop[PositionType](
      getter = PositionType(element.style.position),
      setter = pt => {
        element.style.position = pt.name
        element.style.left = x.toString
        element.style.top = y.toString
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