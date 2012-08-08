package org.hyperscala.editor.validation

import org.powerscala.property.StandardProperty
import org.hyperscala.editor.ValueEditor

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class EmptyStringValidator extends Validator[String] {
  def validate(editor: ValueEditor[String]) = editor.property() match {
    case null | "" => Some(ValidationError(generatorErrorMessage(editor.property), editor))
    case _ => None
  }

  def generatorErrorMessage(property: StandardProperty[String]) = "The %s field must not be empty".format(property.name())
}
