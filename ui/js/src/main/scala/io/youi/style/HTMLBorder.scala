package io.youi.style

case class HTMLBorder(width: Double, style: HTMLBorderStyle, color: Color)

object HTMLBorder extends Stringify[HTMLBorder] {
  lazy val empty: HTMLBorder = HTMLBorder(-1.0, HTMLBorderStyle.Hidden, Color.Clear)

  override def fromString(value: String): Option[HTMLBorder] = None

  override def toString(value: HTMLBorder): Option[String] = if (value.width > 0.0) {
    Some(p"${value.width}px ${value.style.value} ${value.color.toRGBA}")
  } else {
    None
  }
}