package org.hyperscala.context

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class ContextEntry[T](name: String)(implicit val context: Context) {
  def apply() = context.apply[T](name)
  def value: T = apply()
  def get() = context.get[T](name)
  def getOrElse(default: => T) = context.getOrElse[T](name, default)

  def value_=(value: T) = {
    context.set(name, value)
    onChange(value)
  }
  def :=(value: T) = {
    context.set(name, value)
    onChange(value)
  }

  def onChange(value: T) = {}
}
