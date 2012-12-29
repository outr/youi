package org.hyperscala.svg

import attributes.Point
import traits.Shape
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Polyline extends Shape {
  lazy val xmlLabel = "polyline"

  val points = PropertyAttribute[List[Point]]("points", Nil)
}
