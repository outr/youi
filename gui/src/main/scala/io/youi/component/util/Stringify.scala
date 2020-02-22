package io.youi.component.util

trait Stringify[T] {
  def fromString(value: String): Option[T]
  def toString(value: T): Option[String]
}

object Stringify {
  def apply[T](to: T => Option[String])(from: String => Option[T]): Stringify[T] = {
    new Stringify[T] {
      override def fromString(value: String): Option[T] = from(value)

      override def toString(value: T): Option[String] = to(value)
    }
  }
}