package io.youi.spatial.prototype

import io.youi.spatial.prototype.ops.Matrix3Multiply
import io.youi.spatial.Matrix3ArrayPool

trait Matrix3 {
  def m00: Double
  def m01: Double
  def m02: Double
  def m10: Double
  def m11: Double
  def m12: Double
  def m20: Double
  def m21: Double
  def m22: Double

  protected def duplicate(m00: Double = m00,
                          m01: Double = m01,
                          m02: Double = m02,
                          m10: Double = m10,
                          m11: Double = m11,
                          m12: Double = m12,
                          m20: Double = m20,
                          m21: Double = m21,
                          m22: Double = m22): Matrix3

  protected def duplicateAsArray(f: Array[Double] => Unit): Matrix3 = {
    Matrix3ArrayPool.use { array =>
      toArray(array)
      f(array)
      duplicate(
        m00 = array(0),
        m01 = array(1),
        m02 = array(2),
        m10 = array(3),
        m11 = array(4),
        m12 = array(5),
        m20 = array(6),
        m21 = array(7),
        m22 = array(8)
      )
    }
  }

  def toArray(array: Array[Double] = new Array[Double](9)): Array[Double] = {
    array(0) = m00
    array(1) = m01
    array(2) = m02
    array(3) = m10
    array(4) = m11
    array(5) = m12
    array(6) = m20
    array(7) = m21
    array(8) = m22
    array
  }

  def *(that: Matrix3): Matrix3 = Matrix3Multiply(this, that, duplicate)

  def mutable: MutableMatrix3 = MutableMatrix3(m00, m01, m02, m10, m11, m12, m20, m21, m22)
  def immutable: ImmutableMatrix3 = ImmutableMatrix3(m00, m01, m02, m10, m11, m12, m20, m21, m22)
}