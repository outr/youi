package org.hyperscala.ui.widgets.editable

import org.powerscala.reflect._
import org.powerscala.property.backing.CaseValueVariableBacking
import org.powerscala.property.MutableProperty

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
trait PropertyAttributeEditable[P, T] {
  this: Editable[T, _] =>

  def wrapperProperty: MutableProperty[P]
  def wrapperClass: Class[P]
  def fieldName: String

  override def backing = new CaseValueVariableBacking[P, T](wrapperProperty, wrapperClass.caseValue(fieldName).get)
}
