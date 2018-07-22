package io.youi.spatial

import io.youi.util.SizeUtility

sealed trait Size extends SpatialValue[Size] {
  def width: Double
  def height: Double

  def *(d: Double): Size = Size(width * d, height * d)
  def /(d: Double): Size = Size(width / d, height / d)
  def +(d: Double): Size = Size(width + d, height + d)
  def -(d: Double): Size = Size(width - d, height - d)

  def *(that: Size): Size = Size(this.width * that.width, this.height * that.height)
  def /(that: Size): Size = Size(this.width / that.width, this.height / that.height)
  def +(that: Size): Size = Size(this.width + that.width, this.height + that.height)
  def -(that: Size): Size = Size(this.width - that.width, this.height - that.height)

  def set(width: Double, height: Double): Size
  def set(that: Size): Size = set(that.width, that.height)
  def duplicate(): Size
  override def equals(obj: scala.Any): Boolean = obj match {
    case that: Size => width == that.width && height == that.height
    case _ => false
  }

  def scale(width: Option[Double] = None,
            height: Option[Double] = None): Size = SizeUtility.size(width, height, this)

  override def toString: String = s"Size(width: $width, height: $height)"
}

class MutableSize(var width: Double = 0.0, var height: Double = 0.0) extends Size {
  override def set(width: Double = width, height: Double = height): Size = {
    this.width = width
    this.height = height
    this
  }

  override def duplicate(): Size = new MutableSize(width, height)

  override def isMutable: Boolean = true
  override def mutable: MutableSize = this
  override def immutable: ImmutableSize = ImmutableSize(width, height)
}

case class ImmutableSize(width: Double = 0.0, height: Double = 0.0) extends Size {
  override def set(width: Double, height: Double): Size = ImmutableSize(width, height)

  override def duplicate(): Size = ImmutableSize(width, height)

  override def isMutable: Boolean = false
  override def mutable: MutableSize = new MutableSize(width, height)
  override def immutable: ImmutableSize = this
}

object Size {
  lazy val zero: Size = apply()

  def apply(width: Double = 0.0, height: Double = 0.0): Size = ImmutableSize(width, height)
  def mutable(width: Double = 0.0, height: Double = 0.0): MutableSize = new MutableSize(width, height)
}