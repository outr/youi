package io.youi

import reactify._

trait Updates {
  lazy val delta: Channel[Double] = Channel[Double]

  def nextFrame(f: => Unit): Unit = delta.once(_ => f)

  def update(delta: Double): Unit = this.delta := delta
}