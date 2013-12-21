package org.hyperscala.jquery

import org.hyperscala.selector.{StringSelector, Selector}
import org.hyperscala.html.HTMLTag

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  def $(s: String, quoted: Boolean = true) = if (quoted) {
    new jQuerySelector(Selector(s))
  } else {
    new jQuerySelector(StringSelector(s, quoted = false))
  }

  def $(t: HTMLTag) = new jQuerySelector(Selector.id(t))

  def $(selector: Selector) = new jQuerySelector(selector)

  implicit def selector2jqs(selector: Selector) = new jQuerySelector(selector)
}