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

  def offset() = new jQueryOffset(this)
}

class jQueryOffset(jqs: jQuerySelector) {
  lazy val left = NumericStatement(s"${jqs.content}.offset().left")
  lazy val top = NumericStatement(s"${jqs.content}.offset().top")
}