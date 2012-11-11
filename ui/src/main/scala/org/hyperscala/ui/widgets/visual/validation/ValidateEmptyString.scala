package org.hyperscala.ui.widgets.visual.validation

/**
 * @author Matt Hicks <matt@outr.com>
 */
object ValidateEmptyString extends Function1[String, Either[Option[String], String]] {
  def apply(s: String) = if (s == null || s.isEmpty) {
    Right("This field is required")
  } else {
    Left(Some(s))
  }
}
