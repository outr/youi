package io.youi.theme

trait Stringify[T] {
  def fromString(value: String): Option[T]
  def toString(value: T): Option[String]
}
