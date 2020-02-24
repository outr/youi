package io.youi.component.types

import io.youi.{Color, Stringify}
import perfolation._

case class Border(width: Double, style: BorderStyle, color: Color)

object Border extends Stringify[Border] {
  lazy val empty: Border = Border(-1.0, BorderStyle.Hidden, Color.Clear)

  override def fromString(value: String): Option[Border] = None

  override def toString(value: Border): Option[String] = if (value.width > 0.0) {
    Some(p"${value.width}px ${value.style.value} ${value.color.toRGBA}")
  } else {
    None
  }
}