package io.youi.component.types

import io.youi.{Color, Stringify}

/**
  * Represents a CSS box-shadow value.
  *
  * @param offsetX  horizontal offset in pixels (negative shifts left)
  * @param offsetY  vertical offset in pixels (negative shifts up)
  * @param blur     blur radius in pixels; must be >= 0 for a real shadow
  * @param spread   spread radius in pixels (negative shrinks the shadow)
  * @param color    shadow color
  * @param inset    if true, renders as an inner shadow
  */
case class BoxShadow(offsetX: Double,
                     offsetY: Double,
                     blur: Double = 0.0,
                     spread: Double = 0.0,
                     color: Color = Color.Black,
                     inset: Boolean = false)

object BoxShadow extends Stringify[BoxShadow] {
  /** Sentinel value meaning "no shadow / clear the property". */
  lazy val none: BoxShadow = BoxShadow(0.0, 0.0, blur = -1.0)

  override def fromString(value: String): Option[BoxShadow] = None

  override def toString(value: BoxShadow): Option[String] = if (value.blur >= 0.0) {
    val insetStr = if (value.inset) "inset " else ""
    Some(s"${insetStr}${value.offsetX}px ${value.offsetY}px ${value.blur}px ${value.spread}px ${value.color.toRGBA}")
  } else {
    None  // blur < 0 is the sentinel: clear the CSS property
  }
}
