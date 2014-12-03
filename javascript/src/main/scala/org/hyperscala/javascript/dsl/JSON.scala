package org.hyperscala.javascript.dsl

/**
 * @author Matt Hicks <matt@outr.com>
 */
object JSON {
  def stringify(json: Statement[Any]) = MultiStatement[String](sideEffects = false, "JSON.stringify(", json, ")")
}

trait JSON