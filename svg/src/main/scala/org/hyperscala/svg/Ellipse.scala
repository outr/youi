package org.hyperscala.svg

import traits.Shape
import org.hyperscala.PropertyAttribute
import org.hyperscala.css.attributes.Length

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Ellipse extends Shape {
  lazy val xmlLabel = "ellipse"

  val cx = PropertyAttribute[Double]("cx", 0.0)
  val cy = PropertyAttribute[Double]("cy", 0.0)
  val rx = PropertyAttribute[Length]("rx", null)
  val ry = PropertyAttribute[Length]("ry", null)
}
