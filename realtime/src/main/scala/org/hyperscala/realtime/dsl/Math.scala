package org.hyperscala.realtime.dsl

import org.hyperscala.javascript.JavaScriptString

/**
 * @author Matt Hicks <matt@outr.com>
 */
object Math {
  def floor(value: Var[Double]) = new JavaScriptString(s"Math.floor(${value.value}})")
}