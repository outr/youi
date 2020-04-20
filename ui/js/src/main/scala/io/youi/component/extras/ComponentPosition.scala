package io.youi.component.extras

trait ComponentPosition extends StringifyImplicits {
  protected def component: Component

  lazy val x: Var[Double] = Var(0.0)
  lazy val y: Var[Double] = Var(0.0)
  lazy val z: Var[Int] = Var(0)

  lazy val left: Var[Double] = x
  lazy val center: Dep[Double, Double] = Dep(left)(_ + (component.size.width / 2.0), _ - (component.size.width / 2.0))
  lazy val right: Dep[Double, Double] = Dep(left)(_ + component.size.width, _ - component.size.width)

  lazy val top: Var[Double] = y
  lazy val middle: Dep[Double, Double] = Dep(top)(_ + (component.size.height / 2.0), _ - (component.size.height / 2.0))
  lazy val bottom: Dep[Double, Double] = Dep(top)(_ + component.size.height, _ - component.size.height)

  lazy val depth: Var[Int] = z
}