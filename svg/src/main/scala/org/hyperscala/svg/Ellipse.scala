package org.hyperscala.svg

import traits.Shape
import org.hyperscala.PropertyAttribute
import org.hyperscala.css.attributes.Length

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Ellipse extends Shape {
  lazy val xmlLabel = "ellipse"

  lazy val cx = PropertyAttribute[Double]("cx", 0.0)
  lazy val cy = PropertyAttribute[Double]("cy", 0.0)
  lazy val rx = PropertyAttribute[Length]("rx", null)
  lazy val ry = PropertyAttribute[Length]("ry", null)
}
