package io.youi.theme

import io.youi._
import io.youi.Cursor
import io.youi.paint.{Border, Paint}
import io.youi.style.Length
import reactify._

trait ComponentTheme extends Theme {
  val cursor: Var[Cursor] = style[Cursor]("cursor", Cursor.Default, StyleConnect.style[Cursor])
  val interactive: Var[Boolean] = style[Boolean]("interactive", true, StyleConnect.style[Boolean])
  val visible: Var[Boolean] = style[Boolean]("visible", true, StyleConnect.style[Boolean], updatesTransform = true)
  val opacity: Var[Double] = style[Double]("opacity", 1.0, StyleConnect.style[Double], updatesRendering = true)
  val background: Var[Paint] = style[Paint]("background", Paint.none, StyleConnect.style[Paint], updatesRendering = true)

  object padding {
    lazy val left: Var[Length] = style[Length]("padding.left", 0.px, StyleConnect.style[Length])
    lazy val right: Var[Length] = style[Length]("padding.right", 0.0, StyleConnect.style[Length])
    lazy val top: Var[Length] = style[Length]("padding.top", 0.0, StyleConnect.style[Length])
    lazy val bottom: Var[Length] = style[Length]("padding.bottom", 0.0, StyleConnect.style[Length])

    def :=(f: => Double): Unit = set(f)

    def set(f: => Double): Unit = {
      left.set(f)
      right.set(f)
      top.set(f)
      bottom.set(f)
    }

    lazy val width: Val[Length] = Val(left + right)
    lazy val height: Val[Length] = Val(top + bottom)
  }

//  val border: Var[Border] = style[Border]("border", Border.empty, StyleConnect.style[Border], updatesTransform = true)
}