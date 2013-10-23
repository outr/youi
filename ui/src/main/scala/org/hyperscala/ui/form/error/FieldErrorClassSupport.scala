package org.hyperscala.ui.form.error

import org.hyperscala.html.HTMLTag
import org.hyperscala.jquery.jQuery
import org.hyperscala.web.Webpage
import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime.Realtime

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait FieldErrorClassSupport extends ErrorSupport {
  protected def className: String

  private var currentFields = List.empty[HTMLTag]

  Webpage().require(jQuery.LatestWithDefault)

  override protected def clearDisplay() = {
    super.clearDisplay()

    currentFields.foreach {
      case t => t.clazz -= className
    }
    currentFields = Nil
  }

  override protected def displayError(error: ErrorMessage) = {
    super.displayError(error)

    error.elements.foreach {
      case t => {
        if (!t.clazz().contains(className)) {
          t.clazz += className
          if (currentFields.isEmpty) {
            Realtime.send($(t).focus())
          }
          currentFields = t :: currentFields
        }
      }
    }
  }
}
