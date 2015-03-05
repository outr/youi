package org.hyperscala.fabricjs

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Circle extends Object("Circle") {
  lazy val endAngle = prop("endAngle", 2 * math.Pi)
  lazy val radius = prop("radius", 0.0)
  lazy val startAngle = prop("startAngle", 0.0)
}
