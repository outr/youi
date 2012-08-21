package org.hyperscala.bean

import org.hyperscala.html.{Label, Div}
import org.hyperscala.editor.ValueEditor
import org.powerscala.reflect.CaseValue

case class BasicBeanField(beanName: String, caseValue: CaseValue, field: ValueEditor[_]) extends Div with BeanField {
  val label = new Label(forElement = caseValue.name, content = caseValue.label)

  field.name := beanName
  field.id := beanName

  contents += label
  contents += field
}
