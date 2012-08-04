package org.hyperscala.bean

import org.hyperscala.html._
import attributes.ButtonType

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class BeanForm[T](val default: T)(implicit val manifest: Manifest[T]) extends Form with BeanContainer[T] {
  method := "post"

  def parentContainer = null

  val button = createButton()

  protected def createButton() = new Button(buttonType = ButtonType.Submit, content = "Submit")

  fields.foreach {
    case field => contents += field
  }
  contents += button
}