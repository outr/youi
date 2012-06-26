package com.outr.webframework.js

/**
 * @author Matt Hicks <mhicks@sgine.org>
 */
case class ExistingVariable[T](ref: String) extends TypedVar[T] {
  override val reference = Some(ref)
}
