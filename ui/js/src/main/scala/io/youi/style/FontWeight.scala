package io.youi.style

class FontWeight(val value: String) extends AnyVal

object FontWeight extends Stringify[FontWeight] {
  val default: FontWeight = new FontWeight("")

  def apply(value: String): FontWeight = new FontWeight(value)

  override def fromString(value: String): Option[FontWeight] = Some(FontWeight(value))

  override def toString(value: FontWeight): Option[String] = Some(value.value)
}