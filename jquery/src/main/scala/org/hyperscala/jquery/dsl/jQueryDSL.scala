package org.hyperscala.jquery.dsl

import org.hyperscala.javascript.{JavaScriptContent, JavaScriptString}

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait jQueryDSL {
  def apply(value: String) = jQuerySelector(value)
}

case class jQuerySelector(selector: String) {
  private def s = s"$$('$selector')"

  def value() = JavaScriptString(s"$$('$selector').val()")

  def value(v: Any) = JavaScriptString(s"$s.val(${JavaScriptContent.toJS(v)})")
}