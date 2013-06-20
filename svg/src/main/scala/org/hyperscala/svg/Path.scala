package org.hyperscala.svg

import traits.Shape
import org.hyperscala.PropertyAttribute

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class Path extends Shape {
  lazy val xmlLabel = "path"

  lazy val d = PropertyAttribute[String]("d", null)
  lazy val pathLength = PropertyAttribute[Int]("pathLength", 0)
}
