package io.youi.form

import io.youi.dom.create
import org.scalajs.dom.html
import org.scalajs.dom.html.Element

case class BootstrapFieldError(input: FormInput) extends FieldError {
  private val errorDiv = create[html.Div]("div")
  errorDiv.classList.add("invalid-tooltip")
  clear()
  input.element.parentElement.appendChild(errorDiv)

  override lazy val element: Option[Element] = Some(errorDiv)

  override def show(message: String): Unit = {
    errorDiv.innerHTML = message
    errorDiv.style.display = "block"
    input.element.classList.add("is-invalid")
  }

  override def clear(): Unit = {
    errorDiv.style.display = "none"
    errorDiv.innerHTML = ""
    input.element.classList.remove("is-invalid")
  }
}