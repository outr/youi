package org.hyperscala.bean

import org.hyperscala.html._

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class BeanForm[T](val default: T)(implicit val manifest: Manifest[T]) extends Form with BeanContainer[T] {
  method := "post"

  val button = createButton()

  protected def createButton() = new Button(buttonType = "submit", content = "Submit")

  fields.foreach {
    case field => contents += field
  }
  contents += button
}