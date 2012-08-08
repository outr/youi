package org.hyperscala.bean

import org.hyperscala.html._
import attributes.ButtonType

/**
 * @author Matt Hicks <mhicks@powerscala.org>
 */
class BeanForm[T](val default: T)(implicit val manifest: Manifest[T]) extends Form with BeanContainer[T] {
  method := "post"

  def parentContainer = null
  def containerName = null

  val button = createButton()

  assignTabs(fields)
  button.tabIndex := nextTab

  protected def createButton() = new Button(buttonType = ButtonType.Submit, content = "Submit")

  fields.foreach {
    case field => contents += field
  }
  fields.head.field match { // Auto focus the first item
    case input: Input => input.autoFocus := true
    case button: Button => button.autoFocus := true
    case select: Select => select.autoFocus := true
    case textArea: TextArea => textArea.autoFocus := true
  }
  contents += button
}