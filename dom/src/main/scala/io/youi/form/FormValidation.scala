package io.youi.form

case class FormValidation(validation: Validation, modes: Set[ValidationMode]) {
  def validate(mode: ValidationMode, input: FormInput): ValidationResult = if (modes.contains(mode)) {
    validation.validate(input)
  } else {
    ValidationResult.Success
  }
}
