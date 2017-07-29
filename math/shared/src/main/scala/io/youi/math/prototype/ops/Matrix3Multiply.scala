package io.youi.math.prototype.ops

import io.youi.math.prototype.Matrix3

object Matrix3Multiply extends Matrix3Modifier {
  override def apply(m1: Matrix3, m2: Matrix3, modifier: Mod): Matrix3 = modifier(
    m1.m00 * m2.m00 + m1.m01 * m2.m10 + m1.m02 * m2.m20,
    m1.m00 * m2.m01 + m1.m01 * m2.m11 + m1.m02 * m2.m21,
    m1.m00 * m2.m02 + m1.m01 * m2.m12 + m1.m02 * m2.m22,
    m1.m10 * m2.m00 + m1.m11 * m2.m10 + m1.m12 * m2.m20,
    m1.m10 * m2.m01 + m1.m11 * m2.m11 + m1.m12 * m2.m21,
    m1.m10 * m2.m02 + m1.m11 * m2.m12 + m1.m12 * m2.m22,
    m1.m20 * m2.m00 + m1.m21 * m2.m10 + m1.m22 * m2.m20,
    m1.m20 * m2.m01 + m1.m21 * m2.m11 + m1.m22 * m2.m21,
    m1.m20 * m2.m02 + m1.m21 * m2.m12 + m1.m22 * m2.m22
  )
}