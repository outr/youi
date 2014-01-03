package org.hyperscala.ui.widgets.visual

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait ValidatableVisual[T] extends Visual[T] {
  type Validation = T => Either[Option[T], String]

  property.changing.on {
    case newValue => if (validateOnChange) {
      validate(newValue)
    } else {
      Some(newValue)
    }
  }

  private var validations = List.empty[Validation]

  def validateOnChange = false

  def hasError: Boolean

  def errorMessage(message: String): Unit

  def validate(): Boolean = {
    validate(property())
    !hasError
  }

  def validate(value: T): Option[T] = {
    errorMessage(null)
    executeValidations(value, validations)
  }

  private def executeValidations(value: T, validations: List[Validation]): Option[T] = {
    if (validations.nonEmpty) {
      val v = validations.head
      v(value) match {
        case Left(option) => option match {
          case Some(t) => executeValidations(t, validations.tail)
          case None => None
        }
        case Right(errorMessage) => {
          this.errorMessage(errorMessage)
          Some(value)
        }
      }
    } else {
      Some(value)
    }
  }

  def +=(validation: Validation) = synchronized {
    validations = (validation :: validations.reverse).reverse
  }

  def -=(validation: Validation) = synchronized {
    validations = validations.filterNot(v => v == validation)
  }
}

object ValidatableVisual {
  /**
   * Returns true if all ValidatableVisuals within this container pass validation.
   */
  def validateAll(container: HTMLTag) = container.byTag[ValidatableVisual[_]].collect {
    case vv if (!vv.validate()) => vv
  }.isEmpty
}