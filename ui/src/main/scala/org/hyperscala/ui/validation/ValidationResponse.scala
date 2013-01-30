package org.hyperscala.ui.validation

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
case class ValidationResponse(result: ValidationResult, message: String)

object ValidationResponse {
  def Error(message: String) = ValidationResponse(ValidationResult.Error, message)
  def Warning(message: String) = ValidationResponse(ValidationResult.Warning, message)
  def Success(message: String = null) = ValidationResponse(ValidationResult.Success, message)
}