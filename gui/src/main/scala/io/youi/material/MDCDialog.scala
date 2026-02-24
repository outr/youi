package io.youi.material

import io.youi.Unique
import io.youi.component.Component
import io.youi.dom
import io.youi.dom._
import io.youi.material.impl.MDCDialogImplementation
import org.scalajs.dom.html
import reactify.{Channel, Var}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCDialog extends Component(dom.create.div) {
  classes := List("mdc-dialog")

  private val titleId: String = Unique()
  private val contentId: String = Unique()

  val titleText: Var[String] = Var("")
  val contentText: Var[String] = Var("")
  val onClosed: Channel[String] = Channel[String]

  private object elements {
    val container: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-dialog__container")
      div
    }
    val surface: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-dialog__surface")
      div.setAttribute("role", "alertdialog")
      div.setAttribute("aria-modal", "true")
      div.setAttribute("aria-labelledby", titleId)
      div.setAttribute("aria-describedby", contentId)
      div
    }
    val title: html.Element = {
      val h2 = dom.create[html.Element]("h2")
      h2.addClasses("mdc-dialog__title")
      h2.id = titleId
      MDCDialog.this.titleText.attachAndFire(h2.innerHTML_=)
      h2
    }
    val content: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-dialog__content")
      div.id = contentId
      MDCDialog.this.contentText.attachAndFire(div.innerHTML_=)
      div
    }
    val actions: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-dialog__actions")
      div
    }
    val scrim: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-dialog__scrim")
      div
    }
  }

  elements.surface.appendChild(elements.title)
  elements.surface.appendChild(elements.content)
  elements.surface.appendChild(elements.actions)
  elements.container.appendChild(elements.surface)
  element.appendChild(elements.container)
  element.appendChild(elements.scrim)

  element.addEventListener("MDCDialog:closed", (evt: org.scalajs.dom.Event) => {
    val detail = evt.asInstanceOf[js.Dynamic].detail
    val action = detail.action.asInstanceOf[String]
    onClosed @= action
  })

  private lazy val adapter: MDCDialogImplementation =
    MaterialComponents.verified(MDCDialog.attachTo(element))

  /** Add an action button to the dialog. */
  def addAction(label: String, action: String, raised: Boolean = false): Unit = {
    val button = dom.create.button
    button.setAttribute("type", "button")
    button.addClasses("mdc-button", "mdc-dialog__button")
    if (raised) button.addClasses("mdc-button--raised")
    button.setAttribute("data-mdc-dialog-action", action)
    val ripple = dom.create.div
    ripple.addClasses("mdc-button__ripple")
    val span = dom.create.span
    span.addClasses("mdc-button__label")
    span.innerHTML = label
    button.appendChild(ripple)
    button.appendChild(span)
    elements.actions.appendChild(button)
  }

  def open(): Unit = adapter.open()
  def close(action: String = ""): Unit = adapter.close(action)
  def isOpen: Boolean = adapter.isOpen
}

@js.native
@JSGlobal("mdc.dialog.MDCDialog")
object MDCDialog extends js.Object {
  def attachTo(element: html.Element): MDCDialogImplementation = js.native
}
