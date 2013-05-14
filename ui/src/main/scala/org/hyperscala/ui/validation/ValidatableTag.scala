package org.hyperscala.ui.validation

import org.hyperscala.html.{FormField, HTMLTag}
import org.powerscala.hierarchy.event.{DescendantProcessor, AncestorProcessor}
import org.powerscala.event.processor.ListProcessor
import org.powerscala.event.Listenable

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class ValidatableTag(t: FormField) {
  type Validator = String => ValidationResponse

  val validateEvent = new ValidatableEventProcessor()(t)

  def addValidation(validator: Validator)(implicit handler: ValidationHandler) = {
    validateEvent.on {
      case evt => {
        val response = validator(t.value())         // Validate the field
        handler.validated(t, response)              // Apply the validation through the handler
        if (response.result == ValidationResult.Success) {
          None
        } else {
          Some(response)
        }
      }
    }
  }

  def validate() = validateEvent.fire(ValidateEvent).isEmpty
}

object ValidatableTag {
  def validateAll(container: HTMLTag) = container.byTag[FormField].collect {
    case vv if (!vv.validate()) => vv
  }.isEmpty

  def validateTag(tag: FormField) = {
    tag.validateEvent.fire(ValidateEvent).isEmpty
  }
}

class ValidateEvent private()

object ValidateEvent extends ValidateEvent

class ValidatableEventProcessor(implicit listenable: Listenable)
  extends ListProcessor[ValidateEvent, ValidationResponse]("validate")
  with AncestorProcessor[ValidateEvent, Option[ValidationResponse], List[ValidationResponse]]
  with DescendantProcessor[ValidateEvent, Option[ValidationResponse], List[ValidationResponse]]