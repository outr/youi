package io.youi.style

class PercentLength(override val value: Double) extends AnyVal with Length {
  override def toHTML: Option[String] = Some(s"$value%")
}
