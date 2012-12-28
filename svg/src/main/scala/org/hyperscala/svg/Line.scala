package org.hyperscala.svg

import traits.Shape
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Line extends Shape {
  lazy val xmlLabel = "line"

  val x1 = PropertyAttribute[Double]("x1", 0.0)
  val y1 = PropertyAttribute[Double]("y1", 0.0)
  val x2 = PropertyAttribute[Double]("x2", 0.0)
  val y2 = PropertyAttribute[Double]("y2", 0.0)
}
