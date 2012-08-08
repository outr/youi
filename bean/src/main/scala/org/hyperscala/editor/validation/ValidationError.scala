package org.hyperscala.editor.validation

import org.hyperscala.editor.ValueEditor

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
case class ValidationError(message: String, editors: ValueEditor[_]*)