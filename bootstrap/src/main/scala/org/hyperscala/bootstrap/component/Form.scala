package org.hyperscala.bootstrap.component

import org.hyperscala.html._

/**
 * @author Matt Hicks <matt@outr.com>
 */
class Form extends tag.Form(clazz = List("form"), role = "form") with BootstrapComponent {
  val inline = boolProp("form-inline")
  val horizontal = boolProp("form-horizontal")
}