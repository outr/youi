package io.youi.component.types

import io.youi.Stringify
import reactify.Var

class Prop[T](getter: => T, setter: T => Unit, callbacks: (() => Unit)*) extends Var[T](getter) {
  private var valueChanging = false

  refresh()
  attach { v =>
    if (!valueChanging) {
      setter(v)
    }
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

  def changing[T, Return](prop: Prop[T])(f: => Return): Return = {
    prop.valueChanging = true
    try {
      f
    } finally {
      prop.valueChanging = false
    }
  }
}