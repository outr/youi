package org.hyperscala.js

case class Constant[T](value: T) extends TypedVar[T] {
  override def reference = Some(JavaScript.outputConstant(value))
}
