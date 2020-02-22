package io.youi.component.types

import io.youi.Stringify
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

object Prop {
  def stringify[T](get: => String,
                   set: String => Unit,
                   stringify: Stringify[T],
                   default: T,
                   callbacks: (() => Unit)*): Prop[T] = new Prop[T](
    stringify.fromString(get).getOrElse(default),
    t => set(stringify.toString(t).getOrElse("")),
    callbacks: _*
  )
}