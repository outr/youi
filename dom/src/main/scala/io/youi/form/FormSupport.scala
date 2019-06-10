package io.youi.form

import io.youi.dom
import io.youi.dom._
import io.youi.util.Time
import org.scalajs.dom.{Event, html}

import scala.concurrent.Future
import scribe.Execution.global

import scala.concurrent.duration.FiniteDuration
import scala.util.{Failure, Success}

trait FormSupport {
  val form: html.Form
  val clearErrorOnFocus: Boolean = true

  private var disabled: List[html.Element] = Nil

  object input {
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
        val future = submit()
        future.onComplete {
          case Success(result) => result.invoke(this)
          case Failure(exception) => {
            scribe.error(exception)
            alert.danger("An internal error occurred")
          }
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
      if (clearFirst) {
        clear()
      }

      val alert = dom.create[html.Div]("div")
      alert.classList.add("alert")
      alert.classList.add("alert-dismissible")
      alert.classList.add(s"alert-${`type`}")
      alert.innerHTML = message
      container.appendChild(alert)

      removeAfter.foreach { d =>
        Time.delay(d).foreach(_ => alert.remove())
      }

      alert
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

  protected def submit(): Future[FormResult]

  protected def onSuccess(): Unit = {
    clear()
    input.all().headOption.foreach(_.focus())
  }

  protected def onFailure(): Unit = {
    input.all().headOption.foreach(_.focus())
  }
}