package org.hyperscala.bean

import org.hyperscala.html.constraints.BodyChild
import org.powerscala.reflect.CaseValue
import org.hyperscala.editor.ValueEditor

trait BeanField extends BodyChild {
  def caseValue: CaseValue
  def field: ValueEditor[_]

  def generateLabelString = caseValue.name.flatMap(c => if (c.isUpper || c.isDigit) " %s".format(c) else c.toString).capitalize
}
