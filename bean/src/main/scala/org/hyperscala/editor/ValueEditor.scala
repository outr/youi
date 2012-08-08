package org.hyperscala.editor

import org.powerscala.property.{ListProperty, StandardProperty}
import org.hyperscala.html.constraints.BodyChild
import validation.Validator

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait ValueEditor[T] extends BodyChild {
  val validators = new StandardProperty[List[Validator[T]]]("validators", Nil) with ListProperty[Validator[T]]

  def property: StandardProperty[T]

  def validate() = validators().map(v => v.validate(this)).collectFirst {
    case Some(error) => error
  }
}