package org.hyperscala.ui

import org.hyperscala.html.FormField
import validation.ValidationResponse

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
package object validation {
  type Validator = String => ValidationResponse

  implicit def tag2ValidatableTag(tag: FormField) = new ValidatableTag(tag)

  val NotEmpty = ConditionalValidator("This field is required", {
    case s => s != null && s.trim != ""
  })

  val IntRequired = ConditionalValidator("Numeric value expected", NotEmpty.isValidators: _*).withValidators({
    case s => try {
      s.toInt
      true
    } catch {
      case exc: NumberFormatException => false
    }
  })

  def IntBetween(min: Int = 1, max: Int = 100) = {
    ConditionalValidator("Numeric value must be between %s and %s".format(min, max), IntRequired.isValidators: _*).withValidators({
      case s => s.toInt >= min && s.toInt <= max
    })
  }
}

class ConditionalValidator(val errorMessage: String, val isValidators: (String => Boolean)*) extends Function1[String, ValidationResponse] {
  def apply(s: String) = if (isValidators.forall(v => v(s))) {
    ValidationResponse.Success()
  } else {
    ValidationResponse.Error(errorMessage)
  }

  def withMessage(message: String) = new ConditionalValidator(message, isValidators: _*)

  def withValidators(isValidators: (String => Boolean)*) = new ConditionalValidator(errorMessage, (this.isValidators ++ isValidators): _*)
}

object ConditionalValidator {
  def apply(errorMessage: String, isValidators: (String => Boolean)*) = {
    new ConditionalValidator(errorMessage, isValidators: _*)
  }
}