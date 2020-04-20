package io.youi

package object spatial {
  /**
   * Our precision value for equality testing
   * Double point precision is awful
   */
  val precision: Double = 0.001

  /**
   * Angle converting sugar
   *
   * @param d the double value to convert
   */
  implicit class DoubleOps(val d: Double) extends AnyVal {
    def <=>(other: Double): Boolean = tolerance(d, other)
  }


  def tolerantEquals(precision: Double)(d1: Double, d2: Double): Boolean = {
    Math.abs(d1 - d2) <= precision
  }

  lazy val tolerance: (Double, Double) => Boolean = tolerantEquals(precision)
}