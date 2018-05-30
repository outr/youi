package io.youi.style

import io.youi.theme.Stringify

class FontFamily(val value: String) extends AnyVal

object FontFamily extends Stringify[FontFamily] {
  val default: FontFamily = new FontFamily("")

  def apply(value: String): FontFamily = new FontFamily(value)

  override def fromString(value: String): Option[FontFamily] = {
    value match {
      case null | "" | "default" | "auto" => None
      case _ => Some(apply(value))
    }
  }

  override def toString(value: FontFamily): Option[String] = {
    Some(value.value)
  }
}