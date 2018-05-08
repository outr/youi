package io.youi.component.extras

trait Classifiable[T] {
  def fromString(value: String): Option[T]
  def toString(value: T): String
}
