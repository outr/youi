package org.hyperscala.ui.validation

import org.powerscala.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed class ValidationResult extends EnumEntry[ValidationResult]

object ValidationResult extends Enumerated[ValidationResult] {
  val Success = new ValidationResult
  val Error = new ValidationResult
  val Warning = new ValidationResult
}
