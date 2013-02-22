package org.hyperscala.ui.validation

import org.hyperscala.html.{FormField, HTMLTag}
import org.powerscala.bus.{RoutingResponse, Routing}

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class ValidatableTag(t: FormField) {
  type Validator = String => ValidationResponse

  def addValidation(validator: Validator)(implicit handler: ValidationHandler) = {
    t.listeners.synchronous {
      case evt: ValidateEvent => {                  // Invoke validation upon receipt of ValidateEvent
        val response = validator(t.value())         // Validate the field
        handler.validated(t, response)              // Apply the validation through the handler
        response.result match {                     // Handle response routing
          case ValidationResult.Success => Routing.Continue
          case ValidationResult.Warning => Routing.Results(List(response))
          case ValidationResult.Error => Routing.Response(response)
        }
      }
    }
  }

  def validate() = {
    val routing = t.fire(new ValidateEvent)
    routing match {
      case rr: RoutingResponse => false       // Return false if failure occurred
      case _ => true                          // Return true for everything else
    }
  }
}

object ValidatableTag {
  def validateAll(container: HTMLTag) = container.byTag[FormField].collect {
    case vv if (!vv.validate()) => vv
  }.isEmpty

  def validateTag(tag: FormField) = {
    val routing = tag.fire(new ValidateEvent)
    routing match {
      case rr: RoutingResponse => false     //
    }
  }
}