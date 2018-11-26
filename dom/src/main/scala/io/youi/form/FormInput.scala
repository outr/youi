package io.youi.form

import io.youi.dom._
import org.scalajs.dom.{Event, html}

class FormInput(val input: html.Input) {
  private val errorDiv = create[html.Div]("div")
  errorDiv.classList.add("red-text")
  error.clear()
  input.parentElement.appendChild(errorDiv)

  input.addEventListener("blur", (_: Event) => {
    validate()
  })

  def option: Option[String] = input.value match {
    case s if s != null && s.trim.nonEmpty => Some(s)
    case _ => None
  }

  def value: String = option.getOrElse(throw new RuntimeException(s"Value is empty for ${input.id}"))

  def show(): Unit = input.style.display = "inline"
  def hide(): Unit = input.style.display = "none"

  def clear(): Unit = input.value = ""

  object error {
    def show(message: String): Unit = {
      errorDiv.innerHTML = message
      errorDiv.style.display = "block"
      input.classList.add("invalid")
    }

    def clear(): Unit = {
      errorDiv.style.display = "none"
      errorDiv.innerHTML = ""
      input.classList.remove("invalid")
    }
  }

  object validation {
    private var list = List.empty[Validation]

    def apply(validation: Validation): FormInput = {
      list = list ::: List(validation)
      FormInput.this
    }

    def all(): List[Validation] = list
  }

  def validate(): Boolean = {
    val v = option
    val results = validation.all().flatMap(_.validate(input, v))
    error.clear()
    if (results.nonEmpty) {
      error.show(results.mkString("<br/>"))
    }
    results.isEmpty
  }
}