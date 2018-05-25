package io.youi.style

import io.youi.theme.Stringify

trait Length extends Any {
  def value: Double

  def op(f: Double => Double): Length = this match {
    case _: PixelLength => Length(f(value))
    case Length.default => f(value) match {
      case 0.0 => Length.default
      case d => Length(d)
    }
  }

  def +(value: Double): Length = op(_ + value)
  def -(value: Double): Length = op(_ - value)
  def *(value: Double): Length = op(_ * value)
  def /(value: Double): Length = op(_ / value)

  def +(l: Length): Length = op(_ + l.value)
  def -(l: Length): Length = op(_ - l.value)
  def *(l: Length): Length = op(_ * l.value)
  def /(l: Length): Length = op(_ / l.value)

  def toHTML: Option[String]
}

object Length extends Stringify[Length] {
  private val PixelRegex = """([\d.-]+)px""".r

  def apply(value: Double): Length = new PixelLength(value)
  case object default extends Length {
    override def value: Double = 0.0

    override def toHTML: Option[String] = None
  }

  override def fromString(value: String): Option[Length] = value match {
    case "" | null => Some(default)
    case PixelRegex(n) => Some(apply(n.toDouble))
    case _ => {
      scribe.warn(s"Unsupported Length value: $value")
      None
    }
  }

  override def toString(value: Length): Option[String] = value.toHTML
}