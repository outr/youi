package org.hyperscala.ui.validation

import org.hyperscala.html._
import constraints.BodyChild
import org.hyperscala.Container

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
class ClassValidationHandler(className: String = "error",
                             classTag: HTMLTag = null,
                             errorContainer: Container[BodyChild] = null) extends ValidationHandler {
  def validated(field: FormField, response: ValidationResponse) = {
    val t = classTag match {
      case null => field
      case _ => classTag
    }
    if (response.result == ValidationResult.Error) {
      if (!t.clazz().contains("error")) {
        t.clazz += "error"
      }
      if (errorContainer != null) {
        errorContainer.contents.replaceWith(new tag.Div(content = response.message))
      }
    } else {
      t.clazz := t.clazz().filterNot(s => s == className)
      if (errorContainer != null) {
        errorContainer.contents.clear()
      }
    }
  }
}