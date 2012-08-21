package org.hyperscala.editor.validation

import org.hyperscala.editor.ValueEditor

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class LengthValidator(name: String, minimum: Int = 0, maximum: Int = Int.MaxValue) extends Validator[String] {
  def validate(editor: ValueEditor[String]) = {
    val value = editor.property()
    if (value == null) {
      Some(ValidationError("Please enter a value for %s".format(name)))
    } else if (value.trim.length < minimum) {
      Some(ValidationError("Value for %s must be at least %s characters".format(name, minimum)))
    } else if (value.trim.length > maximum) {
      Some(ValidationError("Value for %s must be less than %s characters".format(name, maximum)))
    } else {
      None
    }
  }
}