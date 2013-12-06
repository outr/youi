package org.hyperscala.javascript.dsl

import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
class JSHTMLTag[T <: HTMLTag](ts: Statement[T]) {
  def innerHTML = MultiStatement[String](sideEffects = false, ts, ".innerHTML")
  def innerHTML_=(value: Statement[String]) = MultiStatement[Unit](sideEffects = true, ts, ".innerHTML = ", value)
}