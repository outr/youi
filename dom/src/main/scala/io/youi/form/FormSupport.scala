package io.youi.form

import io.youi.dom
import io.youi.dom._
import org.scalajs.dom.{Event, html}

import scala.concurrent.Future
import scribe.Execution.global

trait FormSupport {
  val form: html.Form
  val clearErrorOnFocus: Boolean = true

  private var disabled: List[html.Element] = Nil

  protected object input {
    private var list = List.empty[FormInput]

    def byId(id: String): FormInput = {
      val fi = new FormInput(FormSupport.this, form.byId[html.Element](id))
      list = list ::: List(fi)
      fi
    }

    def byName(name: String): FormInput = {
      val fi = new FormInput(FormSupport.this, form.oneByName[html.Element](name))
      list = list ::: List(fi)
      fi
    }

    def byClass(className: String): FormInput = {
      val fi = new FormInput(FormSupport.this, form.oneByClass[html.Element](className))
      list = list ::: List(fi)
      fi
    }

    def all(): List[FormInput] = list
  }

  def init(): Unit = {
    form.addEventListener("submit", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()

      disable()
      if (validate(ValidationMode.FormSubmit)) {
        submit().onComplete { _ =>
          enable()
        }
      } else {
        enable()
      }
    })
  }

  def enable(): Unit = {
    disabled.foreach {
      case e: html.Input => e.disabled = false
      case e: html.TextArea => e.disabled = false
      case e: html.Button => e.disabled = false
    }
    disabled = Nil
  }

  def disable(): Unit = {
    disabled = form.bySelector[html.Element]("input, textarea, button").toList.collect {
      case e: html.Input if !e.disabled => {
        e.disabled = true
        e
      }
      case e: html.TextArea if !e.disabled => {
        e.disabled = true
        e
      }
      case e: html.Button if !e.disabled => {
        e.disabled = true
        e
      }
    }
  }

  def clear(): Unit = {
    form.byTag[html.Input]("input").foreach(_.value = "")
    form.byTag[html.TextArea]("textarea").foreach(_.value = "")
  }

  object alert {
    private val container = dom.create[html.Div]("div")
    container.insertFirst(form)

    def success(message: String, clearFirst: Boolean = true): Unit = create("success", message, clearFirst)
    def info(message: String, clearFirst: Boolean = true): Unit = create("info", message, clearFirst)
    def warning(message: String, clearFirst: Boolean = true): Unit = create("warning", message, clearFirst)
    def danger(message: String, clearFirst: Boolean = true): Unit = create("danger", message, clearFirst)

    private def create(`type`: String, message: String, clearFirst: Boolean): Unit = {
      if (clearFirst) {
        clear()
      }

      val alert = dom.create[html.Div]("div")
      alert.classList.add("alert")
      alert.classList.add("alert-dismissible")
      alert.classList.add(s"alert-${`type`}")
      alert.innerHTML = message
      container.appendChild(alert)
    }

    def clear(): Unit = {
      container.innerHTML = ""
    }
  }

  def createFieldError(input: FormInput): FieldError = BootstrapFieldError(input)

  def validate(mode: ValidationMode = ValidationMode.OnDemand): Boolean = {
    val invalidFields = input.all().filter(!_.validate(mode))
    if (invalidFields.nonEmpty) {
      invalidFields.head.focus()
    }
    invalidFields.isEmpty
  }

  protected def submit(): Future[Boolean]

  protected def onSuccess(): Unit = {
    clear()
    input.all().headOption.foreach(_.focus())
  }

  protected def onFailure(): Unit = {
    input.all().headOption.foreach(_.focus())
  }
}