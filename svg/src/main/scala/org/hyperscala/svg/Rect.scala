package org.hyperscala.svg

import traits.Shape
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Rect extends Shape {
  lazy val xmlLabel = "rect"

  lazy val x = PropertyAttribute[Double]("x", 0.0)
  lazy val y = PropertyAttribute[Double]("y", 0.0)
  lazy val width = PropertyAttribute[Double]("width", 0.0)
  lazy val height = PropertyAttribute[Double]("height", 0.0)
  lazy val rx = PropertyAttribute[Double]("rx", 0.0)
  lazy val ry = PropertyAttribute[Double]("ry", 0.0)
}
