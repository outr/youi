package youi.form

import org.scalajs.dom.html

trait FieldError {
  def input: FormInput
  def element: Option[html.Element]

  def show(message: String): Unit
  def clear(): Unit
}