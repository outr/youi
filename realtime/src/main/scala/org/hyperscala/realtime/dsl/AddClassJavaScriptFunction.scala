package org.hyperscala.realtime.dsl

import org.hyperscala.selector.Selector
import org.hyperscala.javascript.JSFunction1

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class AddClassJavaScriptFunction(selector: Selector, className: String) extends JSFunction1[Boolean, Unit] {
  def content =
    s"""
      |function(b) {
      | if (b) {
      |   $$('${selector.value}').addClass('$className');
      | } else {
      |   $$('${selector.value}').removeClass('$className');
      | }
      |}
    """.stripMargin
}