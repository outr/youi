package org.hyperscala.svg

import traits.Shape
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Rect extends Shape {
  lazy val xmlLabel = "rect"

  val x = PropertyAttribute[Double]("x", 0.0)
  val y = PropertyAttribute[Double]("y", 0.0)
  val width = PropertyAttribute[Double]("width", 0.0)
  val height = PropertyAttribute[Double]("height", 0.0)
  val rx = PropertyAttribute[Double]("rx", 0.0)
  val ry = PropertyAttribute[Double]("ry", 0.0)
}
