package io.youi.theme

import io.youi.Cursor
import io.youi.paint.Paint
import reactify.Var

trait ComponentTheme extends Theme {
  override protected def defaultThemeParent: Option[Theme] = None

  private def prnt[T](f: ComponentTheme => T, default: => T): T = parentTheme().collect {
    case p: ComponentTheme => p
  }.map(f).getOrElse(default)

  val cursor: Var[Cursor] = prop(prnt(_.cursor, Cursor.Default))
  val interactive: Var[Boolean] = prop(prnt(_.interactive, true))
  val visible: Var[Boolean] = prop(prnt(_.visible, true), updatesTransform = true)
  val background: Var[Paint] = prop(prnt(_.background, Paint.none), updatesRendering = true)

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