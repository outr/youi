package org.hyperscala.jquery

import org.hyperscala.selector.Selector
import org.hyperscala.html.HTMLTag

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  def $(s: String) = new jQuerySelector(Selector(s))

  def $(t: HTMLTag) = new jQuerySelector(Selector.id(t))
}