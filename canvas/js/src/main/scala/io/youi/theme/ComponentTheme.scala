package io.youi.theme

import io.youi.Cursor
import io.youi.paint.{Border, Paint}
import reactify._

trait ComponentTheme extends Theme {
  override protected def defaultThemeParent: Option[Theme] = None

  private def prnt[T](f: ComponentTheme => T, default: => T): T = parentTheme().collect {
    case p: ComponentTheme => p
  }.map(f).getOrElse(default)

  val cursor: Var[Cursor] = prop(prnt(_.cursor, Cursor.Default))
  val interactive: Var[Boolean] = prop(prnt(_.interactive, true))
  val visible: Var[Boolean] = prop(prnt(_.visible, true), updatesTransform = true)
  val opacity: Var[Double] = prop(prnt(_.opacity, 1.0), updatesRendering = true)
  val background: Var[Paint] = prop(prnt(_.background, Paint.none), updatesRendering = true)
  val dpiMultiplier: Var[Double] = prop(prnt(_.dpiMultiplier, 1.0), updatesRendering = true)

  object padding {
    lazy val left: Var[Double] = prop(prnt(_.padding.left, 0.0))
    lazy val right: Var[Double] = prop(prnt(_.padding.right, 0.0))
    lazy val top: Var[Double] = prop(prnt(_.padding.top, 0.0))
    lazy val bottom: Var[Double] = prop(prnt(_.padding.bottom, 0.0))

    def :=(f: => Double): Unit = set(f)

    def set(f: => Double): Unit = {
      left.set(f)
      right.set(f)
      top.set(f)
      bottom.set(f)
    }

    lazy val width: Val[Double] = Val(left + right)
    lazy val height: Val[Double] = Val(top + bottom)
  }

  val border: Var[Border] = prop(prnt(_.border, Border.empty))

  protected def updateTransform(): Unit = {}
  protected def updateRendering(): Unit = {}

  protected[youi] def prop[T](get: => T,
                              set: T => Unit = (_: T) => (),
                              updatesTransform: Boolean = false,
                              updatesRendering: Boolean = false): Var[T] = {
    val v = Var[T](get)
    set(v())
    v.attach { value =>
      set(value)
      if (updatesTransform) {
        updateTransform()
      }
      if (updatesRendering) {
        updateRendering()
      }
    }
    v
  }
}