package org.hyperscala.javascript.dsl

import org.hyperscala.html.HTMLTag
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
object document extends Selector {
  def value = "document"

  def matches(t: HTMLTag) = false

  def getElementById[T <: HTMLTag](id: String) = Element[T](s"document.getElementById('$id')")
}
