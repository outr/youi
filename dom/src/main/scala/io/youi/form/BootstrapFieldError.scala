package io.youi.form

import io.youi.dom.create
import org.scalajs.dom.html

case class BootstrapFieldError(input: FormInput) extends FieldError {
  private val errorDiv = create[html.Div]("div")
  errorDiv.classList.add("invalid-tooltip")
  clear()
  input.input.parentElement.appendChild(errorDiv)

  override def show(message: String): Unit = {
    errorDiv.innerHTML = message
    errorDiv.style.display = "block"
    input.input.classList.add("is-invalid")
  }

  override def clear(): Unit = {
    errorDiv.style.display = "none"
    errorDiv.innerHTML = ""
    input.input.classList.remove("is-invalid")
  }
}