package youi.material

import youi.component.Component
import youi.dom
import youi.dom._
import youi.material.impl.MDCSnackbarImplementation
import org.scalajs.dom.html
import reactify.{Channel, Var}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCSnackbar extends Component(dom.create.div) {
  classes := List("mdc-snackbar")

  val labelText: Var[String] = Var("")
  val actionText: Var[String] = Var("")
  val timeoutMs: Var[Int] = Var(5000)
  val onClosed: Channel[String] = Channel[String]

  private object elements {
    val surface: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-snackbar__surface")
      div.setAttribute("role", "status")
      div.setAttribute("aria-relevant", "additions")
      div
    }
    val label: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-snackbar__label")
      div.setAttribute("aria-atomic", "false")
      MDCSnackbar.this.labelText.attachAndFire(div.innerHTML_=)
      div
    }
    val actions: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-snackbar__actions")
      div.setAttribute("aria-atomic", "true")
      div
    }
    val actionButton: html.Button = {
      val btn = dom.create.button
      btn.setAttribute("type", "button")
      btn.addClasses("mdc-button", "mdc-snackbar__action")
      val ripple = dom.create.div
      ripple.addClasses("mdc-button__ripple")
      val span = dom.create.span
      span.addClasses("mdc-button__label")
      MDCSnackbar.this.actionText.attachAndFire { t =>
        span.innerHTML = t
        btn.style.display = if (t.isEmpty) "none" else ""
      }
      btn.appendChild(ripple)
      btn.appendChild(span)
      btn
    }
  }

  elements.actions.appendChild(elements.actionButton)
  elements.surface.appendChild(elements.label)
  elements.surface.appendChild(elements.actions)
  element.appendChild(elements.surface)

  element.addEventListener("MDCSnackbar:closed", (evt: org.scalajs.dom.Event) => {
    val detail = evt.asInstanceOf[js.Dynamic].detail
    val reason = if (js.isUndefined(detail) || js.isUndefined(detail.reason)) ""
                 else detail.reason.asInstanceOf[String]
    onClosed @= reason
  })

  private val adapter: MDCSnackbarImplementation =
    MaterialComponents.verified(MDCSnackbar.attachTo(element))

  timeoutMs.attach(adapter.timeoutMs_=)

  def open(): Unit = adapter.open()
  def close(reason: String = ""): Unit = adapter.close(reason)
  def isOpen: Boolean = adapter.isOpen
}

@js.native
@JSGlobal("mdc.snackbar.MDCSnackbar")
object MDCSnackbar extends js.Object {
  def attachTo(element: html.Element): MDCSnackbarImplementation = js.native
}
