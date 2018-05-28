package io.youi.theme

import io.youi.{Cursor, _}
import io.youi.paint.Paint
import reactify._

trait ComponentTheme extends Theme {
  val cursor: StyleProp[Cursor] = style[Cursor]("cursor", Cursor.Default, StyleConnect.style[Cursor])
  val interactive: StyleProp[Boolean] = style[Boolean]("interactive", true, StyleConnect.style[Boolean])
  val visible: StyleProp[Boolean] = style[Boolean]("visible", true, StyleConnect.style[Boolean], updatesTransform = true)
  val opacity: StyleProp[Double] = style[Double]("opacity", 1.0, StyleConnect.style[Double], updatesRendering = true)
  val background: StyleProp[Paint] = style[Paint]("background", Paint.none, StyleConnect.style[Paint], updatesRendering = true)

  object padding {
    lazy val left: StyleProp[Double] = style[Double]("padding.left", 0.px, StyleConnect.style[Double])
    lazy val right: StyleProp[Double] = style[Double]("padding.right", 0.0, StyleConnect.style[Double])
    lazy val top: StyleProp[Double] = style[Double]("padding.top", 0.0, StyleConnect.style[Double])
    lazy val bottom: StyleProp[Double] = style[Double]("padding.bottom", 0.0, StyleConnect.style[Double])

    def :=(f: => Double): Unit = set(f)

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