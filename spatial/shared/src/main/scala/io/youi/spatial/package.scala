package io.youi

import reactify.{State, Val}

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

  implicit class NumericSize[T](t: T)(implicit n: Numeric[T]) {
    private val d = n.toDouble(t)

    /**
      * pixels
      */
    def px: Double = d

    /**
      * degrees conversion (360 converts to 1.0)
      */
    def degrees: Double = d / 360.0

    /**
      * radians conversion (2π converts to 1.0)
      */
    def radians: Double = d / (2.0 * math.Pi)

    /**
      * Returns percentage value `of`.
      */
    def percentOf(of: State[Double]): Val[Double] = Val(of.get * (d * 0.01))
  }
}