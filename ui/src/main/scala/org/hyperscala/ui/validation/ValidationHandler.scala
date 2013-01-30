package org.hyperscala.ui.validation

import org.hyperscala.html.FormField

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait ValidationHandler {
  def validated(field: FormField, response: ValidationResponse): Unit
}