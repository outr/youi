package io.youi.math.prototype.ops

import io.youi.math.prototype.Matrix3

trait Matrix3Modifier {
  type Mod = (Double, Double, Double, Double, Double, Double, Double, Double, Double) => Matrix3

  def apply(m1: Matrix3,
            m2: Matrix3,
            modifier: Mod): Matrix3
}
