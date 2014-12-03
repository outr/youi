package org.hyperscala.jquery

import org.hyperscala.selector.Selector
import org.hyperscala.html.HTMLTag

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object dsl {
  def $ = jQuery

  implicit def selector2jqs(selector: Selector) = new jQuerySelector(selector)
}