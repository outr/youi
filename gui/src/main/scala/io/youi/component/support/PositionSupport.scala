package io.youi.component.support

import io.youi.component.types.{PositionType, Prop, SizeProperty}
import io.youi.component.Component
import reactify.{Dep, Var}

import scala.util.Try

trait PositionSupport {
  this: Component =>

  object position {
    lazy val `type`: Prop[PositionType] = new Prop[PositionType](
      get = PositionType(element.style.position),
      set = pt => {
        element.style.position = pt.name
        element.style.left = x.toString
        element.style.top = y.toString
      }
    )
    lazy val x: SizeProperty = new SizeProperty(element.style.left, element.style.left_=)
    lazy val y: SizeProperty = new SizeProperty(element.style.top, element.style.top_=)
    lazy val z: Prop[Int] = new Prop[Int](Try(element.style.zIndex.toInt).getOrElse(0), i => element.style.zIndex = i.toString)

    lazy val left: Var[Double] = x
    lazy val center: Dep[Double, Double] = PositionSupport.this match {
      case c: MeasuredSupport => Dep(left)(_ + (c.measured.width / 2.0), _ - (c.measured.width / 2.0))
      case c: SizeSupport => Dep(left)(_ + (c.size.width / 2.0), _ - (c.size.width / 2.0))
      case _ => Dep(left)(r => r, r => r)
    }
    lazy val right: Dep[Double, Double] = PositionSupport.this match {
      case c: MeasuredSupport => Dep(left)(_ + c.measured.width, _ - c.measured.width)
      case c: SizeSupport => Dep(left)(_ + c.size.width, _ - c.size.width)
      case _ => Dep(left)(r => r, r => r)
    }

    lazy val top: Var[Double] = y
    lazy val middle: Dep[Double, Double] = PositionSupport.this match {
      case c: MeasuredSupport => Dep(top)(_ + (c.measured.height / 2.0), _ - (c.measured.height / 2.0))
      case c: SizeSupport => Dep(top)(_ + (c.size.height / 2.0), _ - (c.size.height / 2.0))
      case _ => Dep(top)(identity, identity)
    }
    lazy val bottom: Dep[Double, Double] = PositionSupport.this match {
      case c: MeasuredSupport => Dep(top)(_ + c.measured.height, _ - c.measured.height)
      case c: SizeSupport => Dep(top)(_ + c.size.height, _ - c.size.height)
      case _ => Dep(top)(identity, identity)
    }

    lazy val depth: Var[Int] = z
  }
}
