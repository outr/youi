package org.hyperscala.bean

import org.hyperscala.html.constraints.BodyChild
import org.powerscala.reflect.CaseValue
import org.hyperscala.editor.ValueEditor

trait BeanField[P, T] extends BodyChild {
  def container: BeanContainer[P]

  def caseValue: CaseValue
  def field: ValueEditor[T]

  def generateLabelString = caseValue.name.flatMap(c => if (c.isUpper || c.isDigit) " %s".format(c) else c.toString).capitalize
}
