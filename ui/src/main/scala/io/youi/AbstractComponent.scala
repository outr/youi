package io.youi

import com.outr.reactify._
import io.youi.html.style.Position

trait AbstractComponent {
  val position = new ComponentPosition(this)

  protected val actualWidth: Var[Double] = Var(0.0)
  protected val actualHeight: Var[Double] = Var(0.0)

  val size = new SizeWithActual(this)
  val scale = new BasePosition(1.0)

  lazy val rotation: Var[Double] = Var(0.0)
  lazy val opacity: Var[Double] = Var(1.0)
  lazy val visible: Var[Boolean] = Var(true)

  protected def init(): Unit = {}
}

class BasePosition(initialValue: Double) {
  lazy val x: Var[Double] = Var(initialValue)
  lazy val y: Var[Double] = Var(initialValue)
}

class ComponentPosition(component: AbstractComponent) extends BasePosition(0.0) {
  lazy val `type`: Var[Position] = Var[Position](Position.Absolute)

  lazy val left: Var[Double] = x
  lazy val center: Dep[Double, Double] = Dep(left, component.size.actual.width / 2.0)
  lazy val right: Dep[Double, Double] = Dep(left, component.size.actual.width)

  lazy val top: Var[Double] = y
  lazy val middle: Dep[Double, Double] = Dep(top, component.size.actual.height / 2.0)
  lazy val bottom: Dep[Double, Double] = Dep(top, component.size.actual.height)
}

class Size(component: AbstractComponent) {
  lazy val width: Var[Double] = Var(0.0)
  lazy val height: Var[Double] = Var(0.0)
}

class SizeWithActual(component: AbstractComponent) extends Size(component) {
  val actual = new Size(component)
}