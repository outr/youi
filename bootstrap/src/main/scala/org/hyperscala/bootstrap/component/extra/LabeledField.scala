package org.hyperscala.bootstrap.component.extra

import org.hyperscala.bootstrap.component.FormGroup
import org.hyperscala.html.FormField
import org.hyperscala.html.tag

object LabeledField {
  def apply(labelText: String, field: FormField): FormGroup = {
    val group = new FormGroup
    group.contents += new tag.Label(
      forElement = field.identity,
      content = labelText,
      clazz = List("col-sm-3", "control-label")
    )

    field.clazz += "form-control"
    group.contents += new tag.Div(clazz = List("col-sm-9"), content = field)
    group
  }
}
