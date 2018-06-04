package io.youi.theme

import io.youi.style.{Overflow, PointerEvents}

trait HTMLComponentTheme extends ComponentTheme {
  lazy val rotation: StyleProp[Double] = style[Double]("rotation", 0.0, StyleConnect.style[Double](new Stringify[Double] {
    override def fromString(value: String): Option[Double] = None

    override def toString(value: Double): Option[String] = if (value != 0.0) Some(s"rotate(${value * 360.0}deg)") else None
  }), updatesTransform = true)

  lazy val pointerEvents: StyleProp[PointerEvents] = style[PointerEvents]("pointer-events", PointerEvents.Auto, StyleConnect.style[PointerEvents])

  object overflow {
    lazy val x: StyleProp[Overflow] = style[Overflow]("overflow-x", Overflow.Visible, StyleConnect.style[Overflow])
    lazy val y: StyleProp[Overflow] = style[Overflow]("overflow-y", Overflow.Visible, StyleConnect.style[Overflow])

    def :=(overflow: Overflow): Unit = {
      x := overflow
      y := overflow
    }
  }
}