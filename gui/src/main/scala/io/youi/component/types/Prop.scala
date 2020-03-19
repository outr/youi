package io.youi.component.types

import io.youi.Stringify
import reactify.Var

class Prop[T](getter: => T, setter: T => Unit, callbacks: (() => Unit)*) extends Var[T](getter) {
  refresh()
  attach { v =>
    setter(v)
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