package io.youi.theme

import io.youi.style.PointerEvents

trait HTMLComponentTheme extends ComponentTheme {
  lazy val rotation: StyleProp[Double] = style[Double]("rotation", 0.0, StyleConnect.style[Double](new Stringify[Double] {
    override def fromString(value: String): Option[Double] = None

    override def toString(value: Double): Option[String] = if (value != 0.0) Some(s"rotate(${value * 360.0}deg)") else None
  }), updatesTransform = true)

  lazy val pointerEvents: StyleProp[PointerEvents] = style[PointerEvents]("pointer-events", PointerEvents.Auto, StyleConnect.style[PointerEvents])
}