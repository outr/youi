package org.hyperscala.javascript.dsl

import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
object document {
  def getElementById[T <: HTMLTag](id: String) = Element[T](s"document.getElementById('$id')")
}
