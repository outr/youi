package org.hyperscala

import org.hyperscala.jquery.dsl.jQuerySelector

import scala.language.implicitConversions
import org.hyperscala.html.tag.Canvas
import org.hyperscala.selector.Selector

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object jquery {
  implicit def jqs2zoomooz(s: jQuerySelector) = new Zoomooz(s)

  implicit def jqs2jCanvas(s: jQuerySelector) = new jCanvas(s)
  implicit def canvas2jCanvas(c: Canvas) = new jCanvas(Selector.id(c))
}
