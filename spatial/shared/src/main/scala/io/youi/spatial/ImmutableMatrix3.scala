package io.youi.spatial

case class ImmutableMatrix3(m00: Double,
                            m01: Double,
                            m02: Double,
                            m10: Double,
                            m11: Double,
                            m12: Double,
                            m20: Double,
                            m21: Double,
                            m22: Double) extends Matrix3 {
  override def duplicate(m00: Double = m00,
                         m01: Double = m01,
                         m02: Double = m02,
                         m10: Double = m10,
                         m11: Double = m11,
                         m12: Double = m12,
                         m20: Double = m20,
                         m21: Double = m21,
                         m22: Double = m22): Matrix3 = copy(m00, m01, m02, m10, m11, m12, m20, m21, m22)

  def set(m00: Double = m00,
             m01: Double = m01,
             m02: Double = m02,
             m10: Double = m10,
             m11: Double = m11,
             m12: Double = m12,
             m20: Double = m20,
             m21: Double = m21,
             m22: Double = m22): Matrix3 = copy(m00, m01, m02, m10, m11, m12, m20, m21, m22)

  override def immutable: ImmutableMatrix3 = this
}