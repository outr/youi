package io.youi.spatial

import io.youi.spatial.ops.{Matrix3Addition, Matrix3Multiply, Matrix3Subtraction}

/**
  * Trait representing a matrix
  */
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

  /**
    * Creates a new copy of this Matrix3 with the new values assigned.
    */
  def duplicate(m00: Double = m00,
                m01: Double = m01,
                m02: Double = m02,
                m10: Double = m10,
                m11: Double = m11,
                m12: Double = m12,
                m20: Double = m20,
                m21: Double = m21,
                m22: Double = m22): Matrix3

  def duplicate(that: Matrix3): Matrix3 = duplicate(
    m00 = that.m00,
    m01 = that.m01,
    m02 = that.m02,
    m10 = that.m10,
    m11 = that.m11,
    m12 = that.m12,
    m20 = that.m20,
    m21 = that.m21,
    m22 = that.m22
  )

  /**
    * Updates this Matrix3 if mutable and creates a new instance if immutable.
    */
  def set(m00: Double = m00,
          m01: Double = m01,
          m02: Double = m02,
          m10: Double = m10,
          m11: Double = m11,
          m12: Double = m12,
          m20: Double = m20,
          m21: Double = m21,
          m22: Double = m22): Matrix3

  def set(that: Matrix3): Matrix3 = set(
    m00 = that.m00,
    m01 = that.m01,
    m02 = that.m02,
    m10 = that.m10,
    m11 = that.m11,
    m12 = that.m12,
    m20 = that.m20,
    m21 = that.m21,
    m22 = that.m22
  )

  def toArray(array: Array[Double]): Array[Double] = {
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

  def fromArray(array: Array[Double]): Matrix3 = set(
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

  def withArray(duplicate: Boolean)(f: Array[Double] => Unit): Matrix3 = {
    Matrix3ArrayPool.use { array =>
      toArray(array)
      f(array)
      if (duplicate) {
        this.duplicate(
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
      } else {
        set(
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
  }

  /**
    * Matrix multiplication, returns a new matrix
    *
    * @param that the matrix to multiply against
    * @return the product of the multiplication of both matrices
    */
  def *(that: Matrix3): Matrix3 = Matrix3Multiply(this, that, duplicate)

  /**
    * Matrix addition, returns a new matrix
    *
    * @param that the matrix to add
    * @return the product of the addition
    */
  def +(that: Matrix3): Matrix3 = Matrix3Addition(this, that, duplicate)

  /**
    * Matrix subtraction, returns a new matrix
    *
    * @param that the matrix to subtract
    * @return the product of the addition
    */
  def -(that: Matrix3): Matrix3 = Matrix3Subtraction(this, that, duplicate)

  /*
  Basic scalar operations
   */
  def *(scalar: Double): Matrix3 = withArray(duplicate = true)(_.transform(_ * scalar))
  def +(scalar: Double): Matrix3 = withArray(duplicate = true)(_.transform(_ + scalar))
  def -(scalar: Double): Matrix3 = withArray(duplicate = true)(_.transform(_ - scalar))
  def /(scalar: Double): Matrix3 = withArray(duplicate = true)(_.transform(_ / scalar))

  def mutable: MutableMatrix3 = MutableMatrix3(m00, m01, m02, m10, m11, m12, m20, m21, m22)
  def immutable: ImmutableMatrix3 = ImmutableMatrix3(m00, m01, m02, m10, m11, m12, m20, m21, m22)

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
  def transpose: Matrix3 = set(m01 = m10, m02 = m20, m10 = m01, m12 = m21, m20 = m02, m21 = m12)

  /**
    * Inverts a non singular matrix
    *
    * @return
    */
  def inv(): Matrix3 = {
    val determinant = det()
    if (determinant == 0)
      throw new MathException("Cannot invert a singular matrix")
    else {
      val inverseDet: Double = 1.0 / determinant
      set(
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
      Some(set(
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

  def toRotation(value: Double): Matrix3 = {
    val radians = value * (math.Pi * 2.0)
    val cos = math.cos(radians)
    val sin = math.sin(radians)
    set(
      m00 = cos,
      m10 = sin,
      m20 = 0,
      m01 = -sin,
      m11 = cos,
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
    set(
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
    set(
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
    set(
      m02 = m02 + x,
      m12 = m12 + y
    )
  }

  /**
    * Postmultiplies this matrix by a translation matrix. Postmultiplication is also used by OpenGL ES' 1.x
    * glTranslate/glRotate/glScale.
    *
    * @param x the translation in x
    * @param y the translation in y
    * @return
    */
  def translate(x: Double, y: Double): Matrix3 = Matrix3Multiply(this, Matrix3.Identity.toTranslation(x, y), set)

  /** Postmultiplies this matrix with a (counter-clockwise) rotation matrix. Postmultiplication is also used by OpenGL ES' 1.x
    * glTranslate/glRotate/glScale.
    *
    * @param value is a representation of 1.0 being a complete rotation
    */
  def rotate(value: Double): Matrix3 = Matrix3Multiply(this, Matrix3.Identity.toRotation(value), set)

  /** Postmultiplies this matrix with a scale matrix. Postmultiplication is also used by OpenGL ES' 1.x
    * glTranslate/glRotate/glScale.
    *
    * @param scaleX the scale in x
    * @param scaleY the scale in y
    * @return
    */
  def scale(scaleX: Double, scaleY: Double): Matrix3 = {
    Matrix3Multiply(this, Matrix3.Identity.toScaling(scaleX, scaleY), set)
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

  /**
   * Localize the point to the particular matrix, assuming the matrix encodes a locally translated rotation
   * and translation
   */
  def localize(point: Point): Point = {
    val x = point.x * m00 + point.y * m01 + m02
    val y = point.x * m10 + point.y * m11 + m12
    point.set(x, y)
  }
}

object Matrix3 {
  lazy val Identity: Matrix3 = ImmutableMatrix3(
    m00 = 1.0,
    m01 = 0,
    m02 = 0,
    m10 = 0,
    m11 = 1.0,
    m12 = 0,
    m20 = 0,
    m21 = 0,
    m22 = 1.0
  )

  lazy val Empty: Matrix3 = ImmutableMatrix3(
    m00 = 0,
    m01 = 0,
    m02 = 0,
    m10 = 0,
    m11 = 0,
    m12 = 0,
    m20 = 0,
    m21 = 0,
    m22 = 0
  )
}