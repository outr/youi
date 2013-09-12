package org.hyperscala.realtime.dsl

import org.hyperscala.selector.Selector
import org.hyperscala.javascript.JSFunction1

/**
 * @author Matt Hicks <matt@outr.com>
 */
case class SetValueJavaScriptFunction(selector: Selector) extends JSFunction1[String, Unit] {
  def content =
    s"""
      |function(value) {
      | $$('${selector.value}').val(value);
      |}
    """.stripMargin
}