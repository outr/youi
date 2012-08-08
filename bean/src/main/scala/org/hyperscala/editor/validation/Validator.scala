package org.hyperscala.editor.validation

import org.hyperscala.editor.ValueEditor

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait Validator[T] {
  def validate(editor: ValueEditor[T]): Option[ValidationError]
}
