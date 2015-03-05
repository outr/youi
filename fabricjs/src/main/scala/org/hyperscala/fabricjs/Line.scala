package org.hyperscala.fabricjs

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Line extends Object("Line") {
  lazy val x1 = prop("x1", 0.0)
  lazy val x2 = prop("x2", 0.0)
  lazy val y1 = prop("y1", 0.0)
  lazy val y2 = prop("y2", 0.0)
}
