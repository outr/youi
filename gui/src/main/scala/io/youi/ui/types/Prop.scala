package io.youi.ui.types

import reactify.Var
import reactify.standard.StandardVar

class Prop[T](get: => T, set: T => Unit, callbacks: (() => Unit)*) extends StandardVar[T](get, Var.Mode.Normal, None) {
  refresh()
  attach { v =>
    set(v)
    callbacks.foreach(_())
  }

  def refresh(): Unit = this @= get
}