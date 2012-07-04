package org.hyperscala.value

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
case class StaticValue[T](value: T) extends Value[T]
