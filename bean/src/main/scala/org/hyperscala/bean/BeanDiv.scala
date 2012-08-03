package org.hyperscala.bean

import org.hyperscala.html.Div

// Uses DIV instead of FORM for container
class BeanDiv[T](val default: T)(implicit val manifest: Manifest[T]) extends Div with BeanContainer[T] {
  fields.foreach {
    case field => contents += field
  }
}
