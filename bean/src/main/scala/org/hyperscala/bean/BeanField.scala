package org.hyperscala.bean

import org.hyperscala.html.constraints.BodyChild
import org.powerscala.reflect.CaseValue
import org.hyperscala.editor.ValueEditor

trait BeanField extends BodyChild {
  def caseValue: CaseValue
  def field: ValueEditor[_]
}