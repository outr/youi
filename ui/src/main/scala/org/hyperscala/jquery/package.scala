package org.hyperscala

import org.hyperscala.jquery.dsl.jQuerySelector

import scala.language.implicitConversions
import org.hyperscala.javascript.dsl.JSFunction1
import org.hyperscala.event.MouseWheelEvent

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object jquery {
  implicit def jqs2zoomooz(s: jQuerySelector) = new Zoomooz(s)

  implicit def jqs2MouseWheel(s: jQuerySelector) = new jQueryMouseWheel(s)

  def deferTo(other: jQuerySelector, cancel: Boolean = true): JSFunction1[MouseWheelEvent, Boolean] = {
    val js =
      s"""
        |var other = ${other.content};
        |if (p1.deltaX != 0) {
        | var scrollX = -p1.deltaX * p1.deltaFactor;
        | other.scrollLeft(other.scrollLeft() + scrollX);
        |}
        |if (p1.deltaY != 0) {
        | var scrollY = -p1.deltaY * p1.deltaFactor;
        | other.scrollTop(other.scrollTop() + scrollY);
        |}
      """.stripMargin
    JSFunction1[MouseWheelEvent, Boolean](js)
  }
}
