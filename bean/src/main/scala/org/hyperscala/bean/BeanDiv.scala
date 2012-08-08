package org.hyperscala.bean

import org.hyperscala.html.Div
import org.powerscala.property._
import org.hyperscala.css.attributes.Clear

// Uses DIV instead of FORM for container
class BeanDiv[T](val containerName: String, val parentContainer: BeanContainer[_], val default: T)(implicit val manifest: Manifest[T]) extends Div with BeanContainer[T] {
  style.clear := Clear.Both

  fields.foreach {
    case field => contents += field
  }
}
