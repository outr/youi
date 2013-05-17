package org.hyperscala.svg

import traits.Shape
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Image extends Shape {
  lazy val xmlLabel = "image"

  lazy val x = PropertyAttribute[Double]("x", 0.0)
  lazy val y = PropertyAttribute[Double]("y", 0.0)
  lazy val width = PropertyAttribute[Double]("width", 0.0)
  lazy val height = PropertyAttribute[Double]("height", 0.0)
  lazy val href = PropertyAttribute[String]("xlink:href", null)
  lazy val preserveAspectRatio = PropertyAttribute[Boolean]("preserveAspectRatio", true)
}
