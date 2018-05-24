package io.youi.style

class PixelLength(override val value: Double) extends AnyVal with Length {
  override def toHTML: Option[String] = Some(s"${value}px")
}
