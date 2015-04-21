package org.hyperscala.ui.validation

import org.powerscala.enum.{Enumerated, EnumEntry}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
sealed abstract class ValidationResult extends EnumEntry

object ValidationResult extends Enumerated[ValidationResult] {
  case object Success extends ValidationResult
  case object Error extends ValidationResult
  case object Warning extends ValidationResult

  val values = findValues.toVector
}
