package org.hyperscala.fabricjs

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Polyline extends Object("Polyline") {
  lazy val minX = prop("minX", 0.0)
  lazy val minY = prop("minY", 0.0)
  lazy val points = prop[Array[Double]]("points", null)
}
