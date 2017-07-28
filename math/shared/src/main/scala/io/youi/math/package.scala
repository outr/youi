package io.youi

import scala.math._

package object math {
  val precision: Double = 0.00001

  /**
   * Degrees wrapper
   */
  case class Degrees(value: Double) extends AnyVal

  /**
   * Radians wrapper
   */
  case class Radians(value: Double) extends AnyVal

  /**
   * Angle converting sugar
   *
   * @param d the double value to convert
   */
  implicit class DoubleOps(val d: Double) extends AnyVal {
    def rad: Radians = Radians(d)

    def deg: Degrees = Degrees(d)

    def <=>(other: Double): Boolean = tolerance(d, other)
  }

  implicit class DegToRad(val deg: Degrees) {
    def toRad: Radians = Radians(toRadians(deg.value))
  }

  def tolerantEquals(precision: Double)(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) <= precision
  }

  lazy val tolerance: (Double, Double) => Boolean = tolerantEquals(precision)

}
