package org.hyperscala.bootstrap.component

import org.hyperscala.html._
import org.hyperscala.html.extension.ClassBooleanProperty

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Form extends tag.Form(role = "form") {
  clazz += "form"

  val inline = new ClassBooleanProperty(this, enabled = Some("form-inline"))
  val horizontal = new ClassBooleanProperty(this, enabled = Some("form-horizontal"))
}