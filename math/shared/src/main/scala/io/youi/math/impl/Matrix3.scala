package io.youi.math.impl
import io.youi.math._
import io.youi.math.test.Matrix3ArrayPool

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

  def duplicateAsArray(f: Array[Double] => Unit): Matrix3 = {
    Matrix3ArrayPool.use { array =>
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
    * @param m the matrix to add
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
    duplicateAsArray(f => )
  }

  def +(scalar: Double): Matrix3

  def -(scalar: Double): Matrix3

  def /(scalar: Double): Matrix3

  protected def scalarOp(op: (Double, Double) => Double)(
      scalar: Double): Array[Double] = {
    val newArray = matrix3Array
    array.copyToArray(newArray)
    newArray.map(op(_, scalar))
  }

  protected def scalarAdd(scalar: Double): Array[Double] =
    scalarOp(_ + _)(scalar)

  protected def scalarSubtract(scalar: Double): Array[Double] =
    scalarOp(_ - _)(scalar)

  protected def scalarMult(scalar: Double): Array[Double] =
    scalarOp(_ * _)(scalar)

  protected def scalarDiv(scalar: Double): Array[Double] =
    scalarOp(_ / _)(scalar)

  protected def arrayAdd(left: Array[Double],
                         right: Array[Double]): Array[Double] = {
    (left, right).zipped.map(_ + _)
  }

  protected def arraySubtract(left: Array[Double],
                              right: Array[Double]): Array[Double] = {
    (left, right).zipped.map(_ - _)
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
  def transpose: Matrix3

  /**
    * Inverts a non singular matrix
    *
    * @return
    */
  def inv(): Matrix3

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
  def toRotation(rad: Radians): Matrix3

  /**
    * Set to translation matrix
    *
    * @param x the translation in x
    * @param y the translation in y
    * @return
    */
  def toTranslation(x: Double, y: Double): Matrix3

  /**
    * Set to scaling matrix
    *
    * @param scaleX the scale in x
    * @param scaleY the scale in y
    * @return
    */
  def toScaling(scaleX: Double, scaleY: Double): Matrix3

  /**
    * Add a translational component to the matrix in the 3rd column
    *
    * @param x the x-component of the translation vector
    * @param y the y-component of the translation vector
    * @return
    */
  def trn(x: Double, y: Double): Matrix3

  protected def translateArray(x: Double, y: Double): Array[Double] = {
    val idt = identityArray
    idt(M02) = x
    idt(M12) = y
    arrayMatrixMult(array, idt)
  }

  /** Postmultiplies this matrix by a translation matrix. Postmultiplication is also used by OpenGL ES' 1.x
    * glTranslate/glRotate/glScale.
    *
    * @param x the translation in x
    * @param y the translation in y
    * @return
    */
  def translate(x: Double, y: Double): Matrix3

  protected def rotateArray(radians: Radians): Array[Double] = {
    val cosineVal = stdmath.cos(radians.value)
    val sineVal = stdmath.sin(radians.value)
    val newArr = matrix3Array
    newArr(M00) = cosineVal
    newArr(M10) = sineVal

    newArr(M01) = -sineVal
    newArr(M02) = cosineVal

    newArr(M22) = 1

    arrayMatrixMult(array, newArr)
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
  def rotate(radians: Radians): Matrix3

  protected def scaleArray(scaleX: Double, scaleY: Double): Array[Double] = {
    val newArray = matrix3Array

    newArray(M00) = scaleX
    newArray(M11) = scaleY
    newArray(M22) = 1
    arrayMatrixMult(array, newArray)
  }

  /** Postmultiplies this matrix with a scale matrix. Postmultiplication is also used by OpenGL ES' 1.x
    * glTranslate/glRotate/glScale.
    *
    * @param scaleX the scale in x
    * @param scaleY the scale in y
    * @return
    */
  def scale(scaleX: Double, scaleY: Double): Matrix3

  /**
    * Define matrix equality as element equality, _not_ reference equality
    *
    * @param m
    * @return
    */
  def ==(m: Matrix3): Boolean = array.sameElements(m.array)

  override def toString: String = {
    s"""
       |[ ${m00.formatted("%6.5g")} ${m01.formatted("%6.5g")} ${m02.formatted(
         "%6.5g")} ]
       |[ ${m10.formatted("%6.5g")} ${m11.formatted("%6.5g")} ${m12.formatted(
         "%6.5g")} ]
       |[ ${m20.formatted("%6.5g")} ${m21.formatted("%6.5g")} ${m12.formatted(
         "%6.5g")} ]
     """.stripMargin
  }
}

/*
Matrix Companion obj
 */
object Matrix3 {
  val M00 = 0
  val M01 = 3
  val M02 = 6
  val M10 = 1
  val M11 = 4
  val M12 = 7
  val M20 = 2
  val M21 = 5
  val M22 = 8

  /**
    * Return an identity matrix
    *
    * @return
    */
  lazy val Identity: Matrix3 = {
    val ident = matrix3Array
    ident(M00) = 1
    ident(M11) = 1
    ident(M22) = 1
    new ImmutableMatrix3(ident)
  }
}

/**
  * Mutable matrix impl
  */
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

  override def *(other: Matrix3): Matrix3 = {
    MutableMatrix3(
      )
  }

  override def +(m: Matrix3): Matrix3 = {
    returnMat(m, arrayAdd(array, m.array))
  }

  override def -(m: Matrix3): Matrix3 = {
    returnMat(m, arraySubtract(array, m.array))
  }

  /*
  Basic scalar operations
   */
  override def *(scalar: Double): Matrix3 = MutableMatrix3(scalarMult(scalar))

  override def +(scalar: Double): Matrix3 = MutableMatrix3(scalarAdd(scalar))

  override def -(scalar: Double): Matrix3 =
    MutableMatrix3(scalarSubtract(scalar))

  override def /(scalar: Double): Matrix3 = MutableMatrix3(scalarDiv(scalar))

  private[this] def returnMat(ref: Matrix3, arr: Array[Double]) = ref match {
    case _: ImmutableMatrix3 => ImmutableMatrix3(arr)
    case _: MutableMatrix3   => MutableMatrix3(arr)
  }

  def inv(): MutableMatrix3 = {
    val determinant = det()
    if (determinant == 0)
      throw new YouiMathException("Cannot invert a singular matrix")

    val inverseDet: Double = 1.0 / determinant
    val tmp00 = m11 * m22 - m21 * m12
    val tmp10 = m20 * m12 - m10 * m22
    val tmp20 = m10 * m21 - m20 * m11
    val tmp01 = m21 * m02 - m01 * m22
    val tmp11 = m00 * m22 - m20 * m02
    val tmp21 = m20 * m01 - m00 * m21
    val tmp02 = m01 * m12 - m11 * m02
    val tmp12 = m10 * m02 - m00 * m12
    val tmp22 = m00 * m11 - m10 * m01

    m00 = inverseDet * tmp00
    m10 = inverseDet * tmp10
    m20 = inverseDet * tmp20
    m01 = inverseDet * tmp01
    m11 = inverseDet * tmp11
    m21 = inverseDet * tmp21
    m02 = inverseDet * tmp02
    m12 = inverseDet * tmp12
    m22 = inverseDet * tmp22
    this
  }

  def toIdentity: MutableMatrix3 = {
    m00 = 1
    m01 = 0
    m02 = 0
    m10 = 0
    m11 = 1
    m12 = 0
    m20 = 0
    m21 = 0
    m22 = 1
    this
  }

  def *=(other: MutableMatrix3): MutableMatrix3 = {
    val tmp00 = m00 * other.m00 + m01 * other.m10 + m02 * other.m20
    val tmp01 = m00 * other.m01 + m01 * other.m11 + m02 * other.m21
    val tmp02 = m00 * other.m02 + m01 * other.m12 + m02 * other.m22

    val tmp10 = m10 * other.m00 + m11 * other.m10 + m12 * other.m20
    val tmp11 = m10 * other.m01 + m11 * other.m11 + m12 * other.m21
    val tmp12 = m10 * other.m02 + m11 * other.m12 + m12 * other.m22

    val tmp20 = m20 * other.m00 + m21 * other.m10 + m22 * other.m20
    val tmp21 = m20 * other.m01 + m21 * other.m11 + m22 * other.m21
    val tmp22 = m20 * other.m02 + m21 * other.m12 + m22 * other.m22

    m00 = tmp00
    m10 = tmp10
    m20 = tmp20
    m01 = tmp01
    m11 = tmp11
    m21 = tmp21
    m02 = tmp02
    m12 = tmp12
    m22 = tmp22
    this
  }

  def transpose: MutableMatrix3 = {
    val tmp01 = m10
    val tmp02 = m20
    val tmp10 = m01
    val tmp12 = m21
    val tmp20 = m02
    val tmp21 = m12

    m01 = tmp01
    m02 = tmp02
    m10 = tmp10
    m12 = tmp12
    m20 = tmp20
    m21 = tmp21
    this
  }

  def toRotation(radians: Radians): MutableMatrix3 = {
    val cosineVal = stdmath.cos(radians.value)
    val sineVal = stdmath.sin(radians.value)

    m00 = cosineVal
    m10 = sineVal
    m20 = 0

    m01 = -sineVal
    m11 = cosineVal
    m21 = 0

    m02 = 0
    m12 = 0
    m22 = 1
    this
  }

  def toTranslation(x: Double, y: Double): Matrix3 = {
    toIdentity
    m02 = x
    m12 = y
    this
  }

  def toScaling(scaleX: Double, scaleY: Double): Matrix3 = {
    toIdentity
    m00 = scaleX
    m11 = scaleY
    this
  }

  def trn(x: Double, y: Double): Matrix3 = {
    m02 = m02 + xh
    m12 = m12 + y
    this
  }

  override def translate(x: Double, y: Double): Matrix3 = {
    translateArray(x, y).copyToArray(array)
    this
  }

  override def rotate(radians: Radians): Matrix3 = {
    if (radians.value == 0)
      this
    else {
      rotateArray(radians).copyToArray(array)
      this
    }
  }

  override def scale(scaleX: Double, scaleY: Double): Matrix3 = {
    scaleArray(scaleX, scaleY).copyToArray(array)
    this
  }
}

object MutableMatrix3 {
  def apply(arr: Array[Double]): MutableMatrix3 = {
    require(arr.length == 9,
            "Matrix length must be of 9 elements, no more no less")
    new MutableMatrix3(arr)
  }
}

class ImmutableMatrix3(val array: Array[Double] = matrix3Array)
    extends Matrix3 {

  import Matrix3._

  override def *(m: Matrix3): Matrix3 = ImmutableMatrix3(arrayMultiply(m))

  override def +(m: Matrix3): Matrix3 =
    ImmutableMatrix3(arrayAdd(array, m.array))

  override def -(m: Matrix3): Matrix3 =
    ImmutableMatrix3(arraySubtract(array, m.array))

  /*
    Basic scalar operations
   */
  override def *(scalar: Double): Matrix3 = ImmutableMatrix3(scalarMult(scalar))

  override def +(scalar: Double): Matrix3 = ImmutableMatrix3(scalarAdd(scalar))

  override def -(scalar: Double): Matrix3 =
    ImmutableMatrix3(scalarSubtract(scalar))

  override def /(scalar: Double): Matrix3 = ImmutableMatrix3(scalarDiv(scalar))

  def transpose: Matrix3 = {
    val newMatArray = matrix3Array
    newMatArray(M01) = m10
    newMatArray(M02) = m20
    newMatArray(M10) = m01
    newMatArray(M12) = m21
    newMatArray(M20) = m02
    newMatArray(M21) = m12
    ImmutableMatrix3(newMatArray)
  }

  def inv(): Matrix3 = {
    val determinant = det()
    if (determinant == 0)
      throw new YouiMathException("Cannot invert a singular matrix")
    val newMatArray = matrix3Array

    val inverseDet: Double = 1.0 / determinant
    newMatArray(M00) = (m11 * m22 - m21 * m12) * inverseDet
    newMatArray(M10) = (m20 * m12 - m10 * m22) * inverseDet
    newMatArray(M20) = (m10 * m21 - m20 * m11) * inverseDet
    newMatArray(M01) = (m21 * m02 - m01 * m22) * inverseDet
    newMatArray(M11) = (m00 * m22 - m20 * m02) * inverseDet
    newMatArray(M21) = (m20 * m01 - m00 * m21) * inverseDet
    newMatArray(M02) = (m01 * m12 - m11 * m02) * inverseDet
    newMatArray(M12) = (m10 * m02 - m00 * m12) * inverseDet
    newMatArray(M22) = (m00 * m11 - m10 * m01) * inverseDet
    ImmutableMatrix3(newMatArray)
  }

  override def toRotation(radians: Radians): Matrix3 = {
    val cosineVal = stdmath.cos(radians.value)
    val sineVal = stdmath.sin(radians.value)

    val idArray = identityArray
    idArray(M00) = cosineVal
    idArray(M00) = sineVal

    idArray(M01) = -sineVal
    idArray(M11) = cosineVal

    ImmutableMatrix3(idArray)
  }

  override def toTranslation(x: Double, y: Double): Matrix3 = {
    val id = identityArray
    id(M02) = x
    id(M12) = y
    ImmutableMatrix3(id)
  }

  override def toScaling(scaleX: Double, scaleY: Double): Matrix3 = {
    val id = identityArray
    id(M00) = scaleX
    id(M11) = scaleY
    ImmutableMatrix3(id)
  }

  override def trn(x: Double, y: Double): Matrix3 = {
    val copy = matrix3Array
    array.copyToArray(copy)
    copy(M02) += x
    copy(M12) += y
    ImmutableMatrix3(copy)
  }

  override def translate(x: Double, y: Double): Matrix3 = {
    ImmutableMatrix3(translateArray(x, y))
  }

  override def rotate(radians: Radians): Matrix3 = {
    if (radians.value == 0)
      this
    else {
      ImmutableMatrix3(rotateArray(radians))
      this
    }
  }

  override def scale(scaleX: Double, scaleY: Double): Matrix3 = {
    ImmutableMatrix3(scaleArray(scaleX, scaleY))
  }
}

object ImmutableMatrix3 {
  def apply(arr: Array[Double]): ImmutableMatrix3 = {
    require(arr.length == 9,
            "Matrix length must be of 9 elements, no more no less")
    new ImmutableMatrix3(arr)
  }
}
