package io.youi.component

import reactify.Var

class Prop[T](component: Component, get: => T, set: T => Unit) extends Var[T](() => get) {
  attach(set)
  component.props += this

  def update(): Unit = this := get
}