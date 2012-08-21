package org.hyperscala.editor.validation

import org.powerscala.event.Event

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
case class ValidationFailed(error: ValidationError) extends Event