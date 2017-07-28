package io.youi.math

import scala.{math => stdmath}

/**
 * Trait representing a matrix
 *
 */
sealed trait Matrix3 {

  import Matrix3._

  val array: Array[Double]

  /*
 Accessors
   */
  def m00: Double = array(M00)

  def m01: Double = array(M01)

  def m02: Double = array(M02)

  def m10: Double = array(M10)

  def m11: Double = array(M11)

  def m12: Double = array(M12)

  def m20: Double = array(M20)

  def m21: Double = array(M21)

  def m22: Double = array(M22)

  /**
   * Matrix multiplication, returns a new matrix
   *
   * @param m the matrix to multiply against
   * @return the product of the multiplication of both matrices
   */
  def *(m: Matrix3): Matrix3

  protected def arrayMultiply(m: Matrix3): Array[Double] = {
    val newArr = matrix3Array
    newArr(M00) = m00 * m.m00 + m01 * m.m10 + m02 * m.m20
    newArr(M01) = m00 * m.m01 + m01 * m.m11 + m02 * m.m21
    newArr(M02) = m00 * m.m02 + m01 * m.m12 + m02 * m.m22

    newArr(M10) = m10 * m.m00 + m11 * m.m10 + m12 * m.m20
    newArr(M11) = m10 * m.m01 + m11 * m.m11 + m12 * m.m21
    newArr(M12) = m10 * m.m02 + m11 * m.m12 + m12 * m.m22

    newArr(M20) = m20 * m.m00 + m21 * m.m10 + m22 * m.m20
    newArr(M21) = m20 * m.m01 + m21 * m.m11 + m22 * m.m21
    newArr(M22) = m20 * m.m02 + m21 * m.m12 + m22 * m.m22
    newArr
  }

  def arrayMatrixMult(array1: Array[Double],
    array2: Array[Double]): Array[Double] = {
    val newArr = matrix3Array
    newArr(M00) = array1(M00) * array2(M00) + array1(M01) * array2(M10) + array1(M02) * array2(M20)
    newArr(M01) = array1(M00) * array2(M01) + array1(M01) * array2(M11) + array1(M02) * array2(M21)
    newArr(M02) = array1(M00) * array2(M02) + array1(M01) * array2(M12) + array1(M02) * array2(M22)

    newArr(M10) = array1(M10) * array2(M00) + array1(M11) * array2(M10) + array1(M12) * array2(M20)
    newArr(M11) = array1(M10) * array2(M01) + array1(M11) * array2(M11) + array1(M12) * array2(M21)
    newArr(M12) = array1(M10) * array2(M02) + array1(M11) * array2(M12) + array1(M12) * array2(M22)

    newArr(M20) = array1(M20) * array2(M00) + array1(M21) * array2(M10) + array1(M22) * array2(M20)
    newArr(M21) = array1(M20) * array2(M01) + array1(M21) * array2(M11) + array1(M22) * array2(M21)
    newArr(M22) = array1(M20) * array2(M02) + array1(M21) * array2(M12) + array1(M22) * array2(M22)
    newArr
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
  def setToRotationDeg(degrees: Degrees): Matrix3 = {
    setToRotation(degrees.toRad)
  }

  /**
   * Set the matrix to a rotation matrix that will rotate any vector in a couterclockwise direction around
   * the Z axis
   *
   * @param rad the rotation in radians
   * @return
   */
  def setToRotation(rad: Radians): Matrix3

  /**
   * Set to translation matrix
   *
   * @param x the translation in x
   * @param y the translation in y
   * @return
   */
  def setToTranslation(x: Double, y: Double): Matrix3

  /**
   * Set to scaling matrix
   *
   * @param scaleX the scale in x
   * @param scaleY the scale in y
   * @return
   */
  def setToScaling(scaleX: Double, scaleY: Double): Matrix3

  /**
   * Add a translational component to the matrix in the 3rd column
   *
   * @param x the x-component of the translation vector
   * @param y the y-component of the translation vector
   * @return
   */
  def trn(x: Double, y: Double): Matrix3

  /** Postmultiplies this matrix by a translation matrix. Postmultiplication is also used by OpenGL ES' 1.x
   * glTranslate/glRotate/glScale.
   *
   * @param x the translation in x
   * @param y the translation in y
   * @return
   */
  def translate(x: Double, y: Double): Matrix3

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

  /** Postmultiplies this matrix with a scale matrix. Postmultiplication is also used by OpenGL ES' 1.x
   * glTranslate/glRotate/glScale.
   *
   * @param scaleX the scale in x
   * @param scaleY the scale in y
   * @return
   */
  def scale(scaleX: Double, scaleY: Double): Matrix3

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
class MutableMatrix3 private(val array: Array[Double] = matrix3Array)
  extends Matrix3 {

  import Matrix3._

  /*
  Setters
   */
  def m00_=(value: Double): Unit = array(M00) = value

  def m01_=(value: Double): Unit = array(M01) = value

  def m02_=(value: Double): Unit = array(M02) = value

  def m10_=(value: Double): Unit = array(M10) = value

  def m11_=(value: Double): Unit = array(M11) = value

  def m12_=(value: Double): Unit = array(M12) = value

  def m20_=(value: Double): Unit = array(M20) = value

  def m21_=(value: Double): Unit = array(M21) = value

  def m22_=(value: Double): Unit = array(M22) = value


  override def *(m: Matrix3): Matrix3 = {
    val newArr = arrayMultiply(m)
    m match {
      case _: ImmutableMatrix3 => ImmutableMatrix3(newArr)
      case _: MutableMatrix3 => MutableMatrix3(newArr)
    }
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

  def setToRotation(radians: Radians): MutableMatrix3 = {
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

  def setToTranslation(x: Double, y: Double): Matrix3 = {
    toIdentity
    m02 = x
    m12 = y
    this
  }

  def setToScaling(scaleX: Double, scaleY: Double): Matrix3 = {
    toIdentity
    m00 = scaleX
    m11 = scaleY
    this
  }

  def trn(x: Double, y: Double): Matrix3 = {
    m02 = m02 + x
    m12 = m12 + y
    this
  }

  override def translate(x: Double, y: Double): Matrix3 = {
    val idt = identityArray
    idt(M02) = x
    idt(M12) = y
    arrayMatrixMult(array, idt).copyToArray(array)
    this
  }

  override def rotate(radians: Radians): Matrix3 = {
    if (radians.value == 0)
      this
    else {
      val cosineVal = stdmath.cos(radians.value)
      val sineVal = stdmath.sin(radians.value)
      val newArr = matrix3Array
      newArr(M00) = cosineVal
      newArr(M10) = sineVal

      newArr(M01) = -sineVal
      newArr(M02) = cosineVal

      newArr(M22) = 1

      arrayMatrixMult(array, newArr).copyToArray(array)
      this
    }
  }

  override def scale(scaleX: Double, scaleY: Double): Matrix3 = {
    val newArray = matrix3Array

    newArray(M00) = scaleX
    newArray(M11) = scaleY
    newArray(M22) = 1
    arrayMatrixMult(array, newArray).copyToArray(array)
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

  override def *(m: Matrix3): Matrix3 = {
    ImmutableMatrix3(arrayMultiply(m))
  }

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

  override def setToRotation(radians: Radians): Matrix3 = {
    val cosineVal = stdmath.cos(radians.value)
    val sineVal = stdmath.sin(radians.value)

    val idArray = identityArray
    idArray(M00) = cosineVal
    idArray(M00) = sineVal

    idArray(M01) = -sineVal
    idArray(M11) = cosineVal

    ImmutableMatrix3(idArray)
  }

  override def setToTranslation(x: Double, y: Double): Matrix3 = {
    val id = identityArray
    id(M02) = x
    id(M12) = y
    ImmutableMatrix3(id)
  }

  override def setToScaling(scaleX: Double, scaleY: Double): Matrix3 = {
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
    val idt = identityArray
    idt(M02) = x
    idt(M12) = y
    ImmutableMatrix3(arrayMatrixMult(array, idt))
  }

  override def rotate(radians: Radians): Matrix3 = {
    if (radians.value == 0)
      this
    else {
      val cosineVal = stdmath.cos(radians.value)
      val sineVal = stdmath.sin(radians.value)
      val newArr = matrix3Array
      newArr(M00) = cosineVal
      newArr(M10) = sineVal

      newArr(M01) = -sineVal
      newArr(M02) = cosineVal

      newArr(M22) = 1

      ImmutableMatrix3(arrayMatrixMult(array, newArr))
      this
    }
  }

  override def scale(scaleX: Double, scaleY: Double): Matrix3 = {
    val newArray = matrix3Array

    newArray(M00) = scaleX
    newArray(M11) = scaleY
    newArray(M22) = 1
    ImmutableMatrix3(arrayMatrixMult(array, newArray))
  }
}

object ImmutableMatrix3 {
  def apply(arr: Array[Double]): ImmutableMatrix3 = {
    require(arr.length == 9,
      "Matrix length must be of 9 elements, no more no less")
    new ImmutableMatrix3(arr)
  }
}
