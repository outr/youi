package org.hyperscala.js

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
case class ExistingVariable[T](ref: String) extends TypedVar[T] {
  override val reference = Some(ref)
}
