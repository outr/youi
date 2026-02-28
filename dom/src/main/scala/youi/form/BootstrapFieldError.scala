package youi.form

import youi.dom.create
import org.scalajs.dom.html
import org.scalajs.dom.html.Element

case class BootstrapFieldError(input: FormInput) extends FieldError {
  private lazy val errorDiv = {
    val div = create[html.Div]("div")
    div.classList.add("invalid-tooltip")
    input.element.parentElement.appendChild(div)
    div.style.display = "none"
    div
  }

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