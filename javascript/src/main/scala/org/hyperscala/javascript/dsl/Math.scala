package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Math {
  def random() = {
    ExistingStatement[Double]("Math.random()")
  }

  def floor(value: Statement[Double]) = {
    WrappedStatement[Double]("Math.floor(", value, ")", sideEffects = false)
  }
}