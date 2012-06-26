package com.outr.webframework.value


/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
class Property[T] {
  private var _value: Value[T] = _
  private var _modified = false

  def value = _value
  def value_=(_value: Value[T]) = {
    this._value = _value
    _modified = true
  }

  def this(value: Value[T]) = {
    this()
    this.value = value
  }

  def apply() = value

  def apply(v: Value[T]) = value = v

  def :=(v: Value[T]) = value = v

  def apply(t: T) = value = StaticValue(t)

  def :=(t: T) = apply(t)

  def modified = _modified
}
