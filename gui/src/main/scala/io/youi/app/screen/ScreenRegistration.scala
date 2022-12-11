package io.youi.app.screen

import reactify.Var

/**
  * Used in Screen to register variables that only apply during activated state
  *
  * @param v the variable to manage
  * @param value the value that is applied during activation
  * @param previous a holder to retain the previous value
  * @tparam Value the type
  */
case class ScreenRegistration[Value](v: Var[Value], value: () => Value, var previous: Value) {
  def activate(): Unit = {
    previous = v()
    v := value()
  }

  def deactivate(): Unit = {
    v := previous
  }
}