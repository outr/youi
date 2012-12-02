package org.hyperscala.ui.widgets.visual.validation

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait Validation[T] extends Function1[T, Either[Option[T], String]]

object Validation {
  val StringEmpty = error[String]((s: String) => s != null && !s.isEmpty, "This is a required field")
  def StringMinimumLength(minimum: Int) = error[String]((s: String) => s.length >= minimum, "Value must be at least %s characters".format(minimum))
  def StringMaximumLength(maximum: Int) = error[String]((s: String) => s.length <= maximum, "Value must be no more than %s characters".format(maximum))

  def error[T](validator: T => Boolean, message: String) = new Validation[T] {
    def apply(v: T) = if (validator(v)) {
      Left(Some(v))
    } else {
      Right(message)
    }
  }

  def conversion[T](converter: T => T) = new Validation[T] {
    def apply(v: T) = Left(Some(converter(v)))
  }

  def ignore[T](validator: T => Boolean) = new Validation[T] {
    def apply(v: T) = if (validator(v)) {
      Left(Some(v))
    } else {
      Left(None)
    }
  }
}
