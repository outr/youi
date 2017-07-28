package io.youi

import scala.math._

package object math {

  /**
   * Create a new array backing 3d matrices of the correct size
   */
  def matrix3Array: Array[Double] = new Array[Double](9)

  def identityArray: Array[Double] = {
    val id = matrix3Array
    id(Matrix3.M00) = 1
    id(Matrix3.M11) = 1
    id(Matrix3.M22) = 1
    id
  }

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
  implicit class AngleConvert(val d: Double) extends AnyVal {
    def rad: Radians = Radians(d)

    def deg: Degrees = Degrees(d)
  }

  implicit class DegToRad(val deg: Degrees) {
    def toRad: Radians = Radians(toRadians(deg.value))
  }

}
