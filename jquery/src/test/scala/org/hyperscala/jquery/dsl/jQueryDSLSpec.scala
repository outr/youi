package org.hyperscala.jquery.dsl

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

/**
 * @author Matt Hicks <matt@outr.com>
 */
class jQueryDSLSpec extends WordSpec with ShouldMatchers {
  "jQuery DSL" should {
    "generate a simple val() selector" in {
      val js = $("#test").value()
      js.content should equal("$('#test').val()")
    }
    "generate a simple val() String assignment" in {
      val js = $("#test").value("Hello World!")
      js.content should equal("$('#test').val('Hello World!')")
    }
    "generate a simple val() Int assignment" in {
      val js = $("#test").value(123)
      js.content should equal("$('#test').val(123)")
    }
  }
}
