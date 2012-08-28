package org.hyperscala.bean

import org.powerscala.property._
import org.hyperscala.css.attributes.Clear
import org.hyperscala.html.tag.Div

// Uses DIV instead of FORM for container
class BeanDiv[T](val containerName: String, val parentContainer: BeanContainer[_], val default: T)(implicit val manifest: Manifest[T]) extends Div with BeanContainer[T] {
  style.clear := Clear.Both

  fields.foreach {
    case field => contents += field
  }
}
