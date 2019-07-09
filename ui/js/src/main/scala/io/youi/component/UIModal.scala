package io.youi.component

import io.youi.component.bootstrap.Modal
import io.youi.component.extras.HTMLComponent
import io.youi.util.Time
import reactify.Var

import scala.concurrent.duration._
import scribe.Execution.global

class UIModal[C <: Component] private(val component: C,
                                      backdrop: String,
                                      keyboard: Boolean,
                                      focus: Boolean,
                                      show: Boolean ) extends Container with Modal {
  val verticallyCentered: Var[Boolean] = Var(false)

  element.classList.add("modal")
  element.classList.add("fade")     // TODO: make configurable
  element.setAttribute("role", "dialog")
  element.setAttribute("aria-hidden", "true")

  private val document = new Container {
    element.classList.add("modal-dialog")
    element.classList.add("modal-lg")
    element.setAttribute("role", "document")
    size.width := component.size.width
    size.height := component.size.height
  }
  children += document

  private val htmlElement = HTMLComponent.element(component)
  htmlElement.classList.add("modal-content")
  document.children += component

  verticallyCentered.attach { b =>
    if (b) {
      htmlElement.classList.add("modal-dialog-centered")
    } else {
      htmlElement.classList.remove("modal-dialog-centered")
    }
  }

  override protected def init(): Unit = {
    super.init()

    Time.delay(10.millis).foreach { _ =>
      initialize(backdrop, keyboard, focus, show)
    }
  }
}

object UIModal {
  def apply[C <: Component](component: C,
                            backdrop: String = "true",
                            keyboard: Boolean = true,
                            focus: Boolean = true,
                            show: Boolean = false): UIModal[C] = {
    new UIModal[C](component, backdrop, keyboard, focus, show)
  }
}