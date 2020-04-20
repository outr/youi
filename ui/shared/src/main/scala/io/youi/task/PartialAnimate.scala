package io.youi.task

import scala.concurrent.duration.FiniteDuration

case class PartialAnimate(get: () => Double,
                          apply: Double => Unit,
                          destination: () => Double) {
  def from(value: => Double): PartialAnimate = copy(get = () => value)

  def in(duration: FiniteDuration): AnimateIn = AnimateIn(get, apply, destination, duration, Easing.linear)

  def by(stepBy: Double): Task = AnimateBy(stepBy, get, apply, destination)
}