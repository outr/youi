package io.youi.theme

trait HTMLComponentTheme extends ComponentTheme {
  lazy val rotation: StyleProp[Double] = style[Double]("transform", 0.0, StyleConnect.style[Double](new Stringify[Double] {
    override def fromString(value: String): Option[Double] = None

    override def toString(value: Double): Option[String] = if (value != 0.0) Some(s"rotate(${value * 360.0}deg)") else None
  }), updatesTransform = true)

  lazy val pointerEvents: StyleProp[PointerEvents] = style[PointerEvents]("pointer-events", PointerEvents.Auto, StyleConnect.style[PointerEvents])

  object overflow {
    lazy val x: StyleProp[Overflow] = style[Overflow]("overflow-x", Overflow.Visible, StyleConnect.style[Overflow])
    lazy val y: StyleProp[Overflow] = style[Overflow]("overflow-y", Overflow.Visible, StyleConnect.style[Overflow])

    def :=(overflow: => Overflow): Unit = {
      x := overflow
      y := overflow
    }

    def @=(overflow: Overflow): Unit = :=(overflow)
  }
  lazy val userSelect: StyleProp[UserSelect] = style[UserSelect]("user-select", UserSelect.Auto, StyleConnect.style[UserSelect])

  object htmlBorder {
    lazy val top: StyleProp[HTMLBorder] = style[HTMLBorder]("border-top", HTMLBorder.empty, StyleConnect.style[HTMLBorder])
    lazy val bottom: StyleProp[HTMLBorder] = style[HTMLBorder]("border-bottom", HTMLBorder.empty, StyleConnect.style[HTMLBorder])
    lazy val left: StyleProp[HTMLBorder] = style[HTMLBorder]("border-left", HTMLBorder.empty, StyleConnect.style[HTMLBorder])
    lazy val right: StyleProp[HTMLBorder] = style[HTMLBorder]("border-right", HTMLBorder.empty, StyleConnect.style[HTMLBorder])

    object radius {
      lazy val topLeft: StyleProp[Double] = style[Double]("border-top-left-radius", 0.0, StyleConnect.style[Double](pixels))
      lazy val topRight: StyleProp[Double] = style[Double]("border-top-right-radius", 0.0, StyleConnect.style[Double](pixels))
      lazy val bottomLeft: StyleProp[Double] = style[Double]("border-bottom-left-radius", 0.0, StyleConnect.style[Double](pixels))
      lazy val bottomRight: StyleProp[Double] = style[Double]("border-bottom-right-radius", 0.0, StyleConnect.style[Double](pixels))

      def :=(value: => Double): Unit = {
        topLeft := value
        topRight := value
        bottomLeft := value
        bottomRight := value
      }
      def @=(value: Double): Unit = :=(value)

      object top {
        def :=(value: => Double): Unit = {
          topLeft := value
          topRight := value
        }
        def @=(value: Double): Unit = :=(value)
      }
      object bottom {
        def :=(value: => Double): Unit = {
          bottomLeft := value
          bottomRight := value
        }
        def @=(value: Double): Unit = :=(value)
      }
      object left {
        def :=(value: => Double): Unit = {
          topLeft := value
          bottomLeft := value
        }
        def @=(value: Double): Unit = :=(value)
      }
      object right {
        def :=(value: => Double): Unit = {
          topRight := value
          bottomRight := value
        }
        def @=(value: Double): Unit = :=(value)
      }
    }

    def :=(border: => HTMLBorder): Unit = {
      top := border
      bottom := border
      left := border
      right := border
    }
    def @=(border: HTMLBorder): Unit = :=(border)
  }

  lazy val outline: StyleProp[HTMLBorder] = style[HTMLBorder]("outline", HTMLBorder.empty, StyleConnect.style[HTMLBorder])
}