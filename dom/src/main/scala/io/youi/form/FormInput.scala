package io.youi.form

import org.scalajs.dom.{Event, html}

class FormInput(formSupport: FormSupport, val input: html.Input) {
  val error: FieldError = formSupport.createFieldError(this)

  input.required = false
  input.`type` = "text"
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
    val message = results.mkString("<br/>")
    if (results.nonEmpty) {
      error.show(message)
    }
    results.isEmpty
  }
}