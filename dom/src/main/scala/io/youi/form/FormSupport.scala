package io.youi.form

import rapid.Task
import io.youi.dom
import io.youi.dom._
import org.scalajs.dom.{Event, html}

import scala.concurrent.duration.FiniteDuration

trait FormSupport {
  val form: html.Form
  val clearErrorOnFocus: Boolean = true

  private var disabled: List[html.Element] = Nil

  object input {
    private var list = List.empty[FormInput]

    def byId(id: String): FormInput = apply(form.byId[html.Element](id))
    def byName(name: String): FormInput = apply(form.oneByName[html.Element](name))
    def byClass(className: String): FormInput = apply(form.oneByClass[html.Element](className))

    def get(element: html.Element): Option[FormInput] = list.find(_.element == element)

    def apply(element: html.Element): FormInput = get(element) match {
      case Some(fi) => fi
      case None => {
        val fi = new FormInput(FormSupport.this, element)
        list = list ::: List(fi)
        fi
      }
    }

    def all(): List[FormInput] = list
  }

  def initForm(): Unit = {
    form.addEventListener("submit", (evt: Event) => {
      evt.preventDefault()
      evt.stopPropagation()

      submit()
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

  protected lazy val alertContainer: html.Element = {
    val container = dom.create[html.Div]("div")
    container.insertFirst(form)
    container
  }

  object alert {
    def success(message: String,
                clearFirst: Boolean = true,
                removeAfter: Option[FiniteDuration] = None): html.Div = {
      create("success", message, clearFirst, removeAfter)
    }
    def info(message: String,
             clearFirst: Boolean = true,
             removeAfter: Option[FiniteDuration] = None): html.Div = {
      create("info", message, clearFirst, removeAfter)
    }
    def warning(message: String,
                clearFirst: Boolean = true,
                removeAfter: Option[FiniteDuration] = None): html.Div = {
      create("warning", message, clearFirst, removeAfter)
    }
    def danger(message: String,
               clearFirst: Boolean = true,
               removeAfter: Option[FiniteDuration] = None): html.Div = {
      create("danger", message, clearFirst, removeAfter)
    }

    private def create(`type`: String,
                       message: String,
                       clearFirst: Boolean,
                       removeAfter: Option[FiniteDuration]): html.Div = {
      createAlert(`type`, message, clearFirst, removeAfter)
    }

    def clear(): Unit = {
      alertContainer.innerHTML = ""
    }
  }

  protected def createAlert(`type`: String,
                            message: String,
                            clearFirst: Boolean,
                            removeAfter: Option[FiniteDuration]): html.Div = {
    if (clearFirst) {
      alert.clear()
    }

    val a = dom.create[html.Div]("div")
    a.classList.add("alert")
    a.classList.add("alert-dismissible")
    a.classList.add(s"alert-${`type`}")
    a.innerHTML = message
    alertContainer.appendChild(a)

    removeAfter.foreach { d =>
      Task.sleep(d).map { _ =>
        a.remove()
      }.startUnit()
    }

    a
  }

  def createFieldError(input: FormInput): FieldError = BootstrapFieldError(input)

  def validate(mode: ValidationMode = ValidationMode.OnDemand): Boolean = {
    val invalidFields = input.all().filter(!_.validate(mode))
    if (invalidFields.nonEmpty) {
      invalidFields.head.focus()
    }
    invalidFields.isEmpty
  }

  final def submit(): Unit = {
    disable()
    if (validate(ValidationMode.FormSubmit)) {
      process().map { result =>
        result.invoke(this)
      }.handleError { t =>
        Task {
          scribe.error(t)
          alert.danger("An internal error occurred")
          ()
        }
      }.startUnit()
    } else {
      enable()
    }
  }

  protected def process(): Task[FormResult]

  protected def onSuccess(): Unit = {
    clear()
    input.all().headOption.foreach(_.focus())
  }

  protected def onFailure(): Unit = {
    input.all().headOption.foreach(_.focus())
  }
}
