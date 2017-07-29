package io.youi.math

import scala.{math => stdmath}

/**
  * Trait representing a matrix
  *
  */
sealed trait Matrix3 {
  def m00: Double
  def m01: Double
  def m02: Double
  def m10: Double
  def m11: Double
  def m12: Double
  def m20: Double
  def m21: Double
  def m22: Double

  def duplicate(m00: Double = m00,
                m01: Double = m01,
                m02: Double = m02,
                m10: Double = m10,
                m11: Double = m11,
                m12: Double = m12,
                m20: Double = m20,
                m21: Double = m21,
                m22: Double = m22): Matrix3

  def assign(m00: Double = m00,
             m01: Double = m01,
             m02: Double = m02,
             m10: Double = m10,
             m11: Double = m11,
             m12: Double = m12,
             m20: Double = m20,
             m21: Double = m21,
             m22: Double = m22): Matrix3

  def assign(array: Array[Double]): Unit = {
    array(0) = m00
    array(1) = m01
    array(2) = m02
    array(3) = m10
    array(4) = m11
    array(5) = m12
    array(6) = m20
    array(7) = m21
    array(8) = m22
  }

  def duplicateAsArray(f: Array[Double] => Unit): Matrix3 = {
    Matrix3ArrayPool.use { array =>
      assign(array)
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

  /**
    * Matrix multiplication, returns a new matrix
    *
    * @param right the matrix to multiply against
    * @return the product of the multiplication of both matrices
    */
  def *(right: Matrix3): Matrix3 = {
    duplicate(
      m00 * right.m00 + m01 * right.m10 + m02 * right.m20,
      m00 * right.m01 + m01 * right.m11 + m02 * right.m21,
      m00 * right.m02 + m01 * right.m12 + m02 * right.m22,
      m10 * right.m00 + m11 * right.m10 + m12 * right.m20,
      m10 * right.m01 + m11 * right.m11 + m12 * right.m21,
      m10 * right.m02 + m11 * right.m12 + m12 * right.m22,
      m20 * right.m00 + m21 * right.m10 + m22 * right.m20,
      m20 * right.m01 + m21 * right.m11 + m22 * right.m21,
      m20 * right.m02 + m21 * right.m12 + m22 * right.m22
    )
  }

  protected def assignMult(right: Matrix3): Matrix3 = {
    assign(
      m00 * right.m00 + m01 * right.m10 + m02 * right.m20,
      m00 * right.m01 + m01 * right.m11 + m02 * right.m21,
      m00 * right.m02 + m01 * right.m12 + m02 * right.m22,
      m10 * right.m00 + m11 * right.m10 + m12 * right.m20,
      m10 * right.m01 + m11 * right.m11 + m12 * right.m21,
      m10 * right.m02 + m11 * right.m12 + m12 * right.m22,
      m20 * right.m00 + m21 * right.m10 + m22 * right.m20,
      m20 * right.m01 + m21 * right.m11 + m22 * right.m21,
      m20 * right.m02 + m21 * right.m12 + m22 * right.m22
    )
  }

  /**
    * Matrix addition, returns a new matrix
    *
    * @param m the matrix to add
    * @return the product of the addition
    */
  def +(m: Matrix3): Matrix3 = {
    duplicate(
      m00 + m.m00,
      m01 + m.m01,
      m02 + m.m02,
      m10 + m.m10,
      m11 + m.m11,
      m12 + m.m12,
      m20 + m.m20,
      m21 + m.m21,
      m22 + m.m22
    )
  }

  /**
    * Matrix subtraction, returns a new matrix
    *
    * @param m the matrix to subtract
    * @return the product of the addition
    */
  def -(m: Matrix3): Matrix3 = {
    duplicate(
      m00 - m.m00,
      m01 - m.m01,
      m02 - m.m02,
      m10 - m.m10,
      m11 - m.m11,
      m12 - m.m12,
      m20 - m.m20,
      m21 - m.m21,
      m22 - m.m22
    )
  }

  /*
  Basic scalar operations
   */
  def *(scalar: Double): Matrix3 = {
    duplicateAsArray(_.transform(_ * scalar))
  }

  def +(scalar: Double): Matrix3 = {
    duplicateAsArray(_.transform(_ + scalar))
  }

  def -(scalar: Double): Matrix3 = {
    duplicateAsArray(_.transform(_ - scalar))
  }

  def /(scalar: Double): Matrix3 = {
    duplicateAsArray(_.transform(_ / scalar))
  }

  /**
    * The determinant of the matrix
    *
    * @return
    */
  def det(): Double = {
    m00 * m11 * m22 + m01 * m12 * m20 + m02 * m10 * m21 - m00 * m12 * m21 - m01 * m10 * m22 - m02 * m11 * m20
  }

  /**
    * Return a transposed matrix
    *
    * @return
    */
  def transpose: Matrix3 = {
    assign(m01 = m10, m02 = m20, m10 = m01, m12 = m21, m20 = m02, m21 = m12)
  }

  /**
    * Inverts a non singular matrix
    *
    * @return
    */
  def inv(): Matrix3 = {
    val determinant = det()
    if (determinant == 0)
      throw new YouiMathException("Cannot invert a singular matrix")
    else {
      val inverseDet: Double = 1.0 / determinant
      assign(
        m00 = (m11 * m22 - m21 * m12) * inverseDet,
        m01 = (m21 * m02 - m01 * m22) * inverseDet,
        m02 = (m01 * m12 - m11 * m02) * inverseDet,
        m10 = (m20 * m12 - m10 * m22) * inverseDet,
        m11 = (m00 * m22 - m20 * m02) * inverseDet,
        m12 = (m10 * m02 - m00 * m12) * inverseDet,
        m20 = (m10 * m21 - m20 * m11) * inverseDet,
        m21 = (m20 * m01 - m00 * m21) * inverseDet,
        m22 = (m00 * m11 - m10 * m01) * inverseDet
      )
    }
  }

  def safeInverse(): Option[Matrix3] = {
    val determinant = det()
    if (determinant == 0)
      None
    else {
      val inverseDet: Double = 1.0 / determinant
      Some(assign(
        m00 = (m11 * m22 - m21 * m12) * inverseDet,
        m01 = (m21 * m02 - m01 * m22) * inverseDet,
        m02 = (m01 * m12 - m11 * m02) * inverseDet,
        m10 = (m20 * m12 - m10 * m22) * inverseDet,
        m11 = (m00 * m22 - m20 * m02) * inverseDet,
        m12 = (m10 * m02 - m00 * m12) * inverseDet,
        m20 = (m10 * m21 - m20 * m11) * inverseDet,
        m21 = (m20 * m01 - m00 * m21) * inverseDet,
        m22 = (m00 * m11 - m10 * m01) * inverseDet
      ))
    }
  }

  /**
    * Set the matrix to a rotation matrix that will rotate any vector in a couterclockwise direction around
    * the Z axis
    *
    * @param degrees the amount to rotate in degrees
    * @return
    */
  def toRotationDeg(degrees: Degrees): Matrix3 = {
    toRotation(degrees.toRad)
  }

  /**
    * Set the matrix to a rotation matrix that will rotate any vector in a couterclockwise direction around
    * the Z axis
    *
    * @param rad the rotation in radians
    * @return
    */
  def toRotation(rad: Radians): Matrix3 = {
    val cosineVal = stdmath.cos(rad.value)
    val sineVal = stdmath.sin(rad.value)
    assign(
      m00 = cosineVal,
      m10 = sineVal,
      m20 = 0,
      m01 = -sineVal,
      m11 = cosineVal,
      m21 = 0,
      m02 = 0,
      m12 = 0,
      m22 = 1
    )
  }

  /**
    * Set to translation matrix
    *
    * @param x the translation in x
    * @param y the translation in y
    * @return
    */
  def toTranslation(x: Double, y: Double): Matrix3 = {
    assign(
      m00 = 1,
      m01 = 0,
      m02 = x,
      m10 = 0,
      m11 = 1,
      m12 = y,
      m20 = 0,
      m21 = 0,
      m22 = 1
    )
  }

  /**
    * Set to scaling matrix
    *
    * @param scaleX the scale in x
    * @param scaleY the scale in y
    * @return
    */
  def toScaling(scaleX: Double, scaleY: Double): Matrix3 = {
    assign(
      m00 = scaleX,
      m01 = 0,
      m02 = 0,
      m10 = 0,
      m11 = scaleY,
      m12 = 0,
      m20 = 0,
      m21 = 0,
      m22 = 1
    )
  }

  /**
    * Add a translational component to the matrix in the 3rd column
    *
    * @param x the x-component of the translation vector
    * @param y the y-component of the translation vector
    * @return
    */
  def trn(x: Double, y: Double): Matrix3 = {
    assign(
      m02 = m02 + x,
      m12 = m12 + y
    )
  }

  /** Postmultiplies this matrix by a translation matrix. Postmultiplication is also used by OpenGL ES' 1.x
    * glTranslate/glRotate/glScale.
    *
    * @param x the translation in x
    * @param y the translation in y
    * @return
    */
  def translate(x: Double, y: Double): Matrix3 = {
    assignMult(Matrix3.Identity.toTranslation(x, y))
  }

  /** Postmultiplies this matrix with a (counter-clockwise) rotation matrix. Postmultiplication is also used by OpenGL ES' 1.x
    * glTranslate/glRotate/glScale.
    *
    * @param degrees rotation in degrees
    * @return
    */
  def rotateDeg(degrees: Degrees): Matrix3 = {
    if (degrees.value == 0)
      this
    else
      rotate(degrees.toRad)
  }

  /** Postmultiplies this matrix with a (counter-clockwise) rotation matrix. Postmultiplication is also used by OpenGL ES' 1.x
    * glTranslate/glRotate/glScale.
    *
    * @return
    */
  def rotate(radians: Radians): Matrix3 = {
    assignMult(Matrix3.Identity.toRotation(radians))
  }

  /** Postmultiplies this matrix with a scale matrix. Postmultiplication is also used by OpenGL ES' 1.x
    * glTranslate/glRotate/glScale.
    *
    * @param scaleX the scale in x
    * @param scaleY the scale in y
    * @return
    */
  def scale(scaleX: Double, scaleY: Double): Matrix3 = {
    assignMult(Matrix3.Identity.toScaling(scaleX, scaleY))
  }

  def ==(m: Matrix3): Boolean = {
    (m00 <=> m.m00) &&
    (m01 <=> m.m01) &&
    (m02 <=> m.m02) &&
    (m10 <=> m.m10) &&
    (m11 <=> m.m11) &&
    (m12 <=> m.m12) &&
    (m20 <=> m.m20) &&
    (m21 <=> m.m21) &&
    (m22 <=> m.m22)
  }
}

/*
Matrix Companion obj
 */
object Matrix3 {

  /**
    * Return an identity matrix
    *
    * @return
    */
  lazy val Identity: Matrix3 = {
    ImmutableMatrix3(m00 = 1.0,
                     m01 = 0,
                     m02 = 0,
                     m10 = 0,
                     m11 = 1.0,
                     m12 = 0,
                     m20 = 0,
                     m21 = 0,
                     m22 = 1.0)
  }

  lazy val Empty: Matrix3 = {
    ImmutableMatrix3(m00 = 0,
      m01 = 0,
      m02 = 0,
      m10 = 0,
      m11 = 0,
      m12 = 0,
      m20 = 0,
      m21 = 0,
      m22 = 0)
  }
}

case class ImmutableMatrix3(m00: Double,
                            m01: Double,
                            m02: Double,
                            m10: Double,
                            m11: Double,
                            m12: Double,
                            m20: Double,
                            m21: Double,
                            m22: Double)
    extends Matrix3 {
  override def duplicate(m00: Double = m00,
                         m01: Double = m01,
                         m02: Double = m02,
                         m10: Double = m10,
                         m11: Double = m11,
                         m12: Double = m12,
                         m20: Double = m20,
                         m21: Double = m21,
                         m22: Double = m22): Matrix3 =
    copy(m00, m01, m02, m10, m11, m12, m20, m21, m22)

  def assign(m00: Double = m00,
             m01: Double = m01,
             m02: Double = m02,
             m10: Double = m10,
             m11: Double = m11,
             m12: Double = m12,
             m20: Double = m20,
             m21: Double = m21,
             m22: Double = m22): Matrix3 =
    copy(m00, m01, m02, m10, m11, m12, m20, m21, m22)

}

case class MutableMatrix3(var m00: Double,
                          var m01: Double,
                          var m02: Double,
                          var m10: Double,
                          var m11: Double,
                          var m12: Double,
                          var m20: Double,
                          var m21: Double,
                          var m22: Double)
    extends Matrix3 {
  def assign(m00: Double = m00,
             m01: Double = m01,
             m02: Double = m02,
             m10: Double = m10,
             m11: Double = m11,
             m12: Double = m12,
             m20: Double = m20,
             m21: Double = m21,
             m22: Double = m22): Matrix3 = {
    this.m00 = m00
    this.m01 = m01
    this.m02 = m02
    this.m10 = m10
    this.m11 = m11
    this.m12 = m12
    this.m20 = m20
    this.m21 = m21
    this.m22 = m22
    this
  }
  override def duplicate(m00: Double = m00,
                         m01: Double = m01,
                         m02: Double = m02,
                         m10: Double = m10,
                         m11: Double = m11,
                         m12: Double = m12,
                         m20: Double = m20,
                         m21: Double = m21,
                         m22: Double = m22): Matrix3 =
    copy(m00, m01, m02, m10, m11, m12, m20, m21, m22)
}
