package org.hyperscala.svg

import attributes.Point
import traits.Shape
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Polygon extends Shape {
  lazy val xmlLabel = "polygon"

  lazy val points = PropertyAttribute[List[Point]]("points", Nil)
}
