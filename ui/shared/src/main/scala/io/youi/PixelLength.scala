package io.youi

class PixelLength(override val value: Double) extends AnyVal with Length {
  override def isDefault: Boolean = false

  override def toHTML: Option[String] = Some(toString)

  override def toString: String = s"${value}px"
}