package io.youi.math.prototype

trait Matrix3Modifier {
  type Mod = (Double, Double, Double, Double, Double, Double, Double, Double, Double) => Matrix3

  def apply(m1: Matrix3,
            m2: Matrix3,
            modifier: Mod): Matrix3
}
