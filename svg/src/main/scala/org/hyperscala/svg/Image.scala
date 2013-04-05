package org.hyperscala.svg

import traits.Shape
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Image extends Shape {
  lazy val xmlLabel = "image"

  val x = PropertyAttribute[Double]("x", 0.0)
  val y = PropertyAttribute[Double]("y", 0.0)
  val width = PropertyAttribute[Double]("width", 0.0)
  val height = PropertyAttribute[Double]("height", 0.0)
  val href = PropertyAttribute[String]("xlink:href", null)
  val preserveAspectRatio = PropertyAttribute[Boolean]("preserveAspectRatio", true)
}
