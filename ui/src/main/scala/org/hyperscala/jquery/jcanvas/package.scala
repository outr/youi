package org.hyperscala.jquery

import org.hyperscala.jquery.dsl.jQuerySelector
import org.hyperscala.html.tag.Canvas
import org.hyperscala.selector.Selector
import org.powerscala.Color

import scala.language.implicitConversions

/**
 * @author Matt Hicks <matt@outr.com>
 */
package object jcanvas {
  implicit def jqs2jCanvas(s: jQuerySelector) = new jCanvas(s)
  implicit def canvas2jCanvas(c: Canvas) = new jCanvas(Selector.id(c))

  trait CanvasProperty {
    def value: Any
  }

  trait ArcProperty extends CanvasProperty

  trait AllCanvas extends ArcProperty

  implicit class StrokeStyle(val value: Color) extends AllCanvas
  implicit class StrokeWidth(val value: Int) extends AllCanvas
  implicit class X(val value: Int) extends ArcProperty
  implicit class Y(val value: Int) extends ArcProperty
  implicit class Radius(val value: Int) extends ArcProperty
  implicit class Start(val value: Int) extends ArcProperty
  implicit class End(val value: Int) extends ArcProperty
}
