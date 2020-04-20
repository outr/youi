package io.youi.theme

trait ComponentTheme extends Theme {
  val cursor: StyleProp[Cursor] = style[Cursor]("cursor", Cursor.Default, StyleConnect.style[Cursor])
  val interactive: StyleProp[Boolean] = style[Boolean]("interactive", true, StyleConnect.style[Boolean])
  val display: StyleProp[Display] = style[Display]("display", Display.Initial, StyleConnect.style[Display], updatesTransform = true)
  val visibility: StyleProp[Visibility] = style[Visibility]("visibility", Visibility.Visible, StyleConnect.style[Visibility], updatesTransform = true)
  val opacity: StyleProp[Double] = style[Double]("opacity", 1.0, StyleConnect.style[Double], updatesRendering = true)
  val background: StyleProp[Paint] = style[Paint]("background", Paint.none, StyleConnect.style[Paint], updatesRendering = true)

  object padding {
    lazy val left: StyleProp[Double] = style[Double]("padding-left", 0.px, StyleConnect.style[Double])
    lazy val right: StyleProp[Double] = style[Double]("padding-right", 0.0, StyleConnect.style[Double])
    lazy val top: StyleProp[Double] = style[Double]("padding-top", 0.0, StyleConnect.style[Double])
    lazy val bottom: StyleProp[Double] = style[Double]("padding-bottom", 0.0, StyleConnect.style[Double])

    def :=(f: => Double): Unit = set(f)

    def @=(f: Double): Unit = set(f)

    def set(f: => Double): Unit = {
      left.set(f)
      right.set(f)
      top.set(f)
      bottom.set(f)
    }

    lazy val width: Val[Double] = Val(left() + right())
    lazy val height: Val[Double] = Val(top() + bottom())
  }

  object margin {
    lazy val left: StyleProp[Double] = style[Double]("margin-left", 0.px, StyleConnect.style[Double])
    lazy val right: StyleProp[Double] = style[Double]("margin-right", 0.0, StyleConnect.style[Double])
    lazy val top: StyleProp[Double] = style[Double]("margin-top", 0.0, StyleConnect.style[Double])
    lazy val bottom: StyleProp[Double] = style[Double]("margin-bottom", 0.0, StyleConnect.style[Double])

    def :=(f: => Double): Unit = set(f)

    def @=(f: Double): Unit = set(f)

    def set(f: => Double): Unit = {
      left.set(f)
      right.set(f)
      top.set(f)
      bottom.set(f)
    }

    lazy val width: Val[Double] = Val(left() + right())
    lazy val height: Val[Double] = Val(top() + bottom())
  }

//  val border: Var[Border] = style[Border]("border", Border.empty, StyleConnect.style[Border], updatesTransform = true)
}