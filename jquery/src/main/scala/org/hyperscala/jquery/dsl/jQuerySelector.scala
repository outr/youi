package org.hyperscala.jquery.dsl

import org.hyperscala.selector.Selector
import org.hyperscala.javascript.dsl.{NumericStatement, TypedStatement}

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jQuerySelector(selector: Selector) extends TypedStatement[Selector] {
  def content = s"$$('${selector.value}')"

  def width() = NumericStatement(s"$content.width()")
  def height() = NumericStatement(s"$content.height()")
}
