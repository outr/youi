package org.hyperscala.bootstrap.component

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class FormGroup extends tag.Div {
  clazz += "form-group"
}

object FormGroup {
  def apply(labelText: String, field: FormField) = {
    val group = new FormGroup
    group.contents += new tag.Label(forElement = field.identity, content = labelText, clazz = List("col-sm-3", "control-label"))
    field.clazz += "form-control"
    group.contents += new tag.Div(clazz = List("col-sm-9"), content = field)
    group
  }
}