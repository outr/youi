package org.hyperscala.value

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
case class StaticValue[T](value: T) extends Value[T]
