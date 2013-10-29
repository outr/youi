package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Math {
  def floor(value: NumericStatement) = NumericStatement(s"Math.floor(${value.content})")
}
