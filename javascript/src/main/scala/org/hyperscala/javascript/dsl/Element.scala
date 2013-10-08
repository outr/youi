package org.hyperscala.javascript.dsl

import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Element[T <: HTMLTag] extends TypedStatement[T] {

}

object Element {
  def apply[T <: HTMLTag](js: String) = new Element[T] {
    def content = js
  }
}