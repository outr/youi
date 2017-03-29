package io.youi

import reactify._

trait Updates {
  lazy val delta: Val[Double] = Var(0.0)

  def nextFrame(f: => Unit): Unit = delta.once(_ => f)

  def update(d: Double): Unit = delta.asInstanceOf[Var[Double]] := d
}