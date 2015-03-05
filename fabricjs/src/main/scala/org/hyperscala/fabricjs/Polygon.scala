package org.hyperscala.fabricjs

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Polygon extends Object("Polygon") {
  lazy val minX = prop("minxX", 0.0)
  lazy val minY = prop("minY", 0.0)
  lazy val points = prop[Array[Double]]("points", null)
}
