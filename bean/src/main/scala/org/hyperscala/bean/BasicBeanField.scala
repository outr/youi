package org.hyperscala.bean

import org.hyperscala.html.{Label, Div}
import org.hyperscala.editor.ValueEditor
import org.powerscala.reflect.CaseValue

case class BasicBeanField[P, T](container: BeanContainer[P], caseValue: CaseValue, field: ValueEditor[T]) extends Div with BeanField[P, T] {
  val label = new Label(forElement = caseValue.name, content = generateLabelString)

  field.name := caseValue.name
  field.id := caseValue.name

  contents += label
  contents += field
}
