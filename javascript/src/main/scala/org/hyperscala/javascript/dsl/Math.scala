package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Math {
  def floor(value: TypedStatement[Double]) = TypedStatement[Double](s"Math.floor(${value.content})")
}
