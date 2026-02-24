package io.youi.material

import io.youi.component.Component
import io.youi.dom
import io.youi.dom._
import io.youi.event.EventSupport
import io.youi.material.impl.MDCSwitchImplementation
import org.scalajs.dom.html
import reactify.Var

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCSwitch extends Component(dom.create.button) with EventSupport {
  classes := List("mdc-switch")
  element.setAttribute("type", "button")
  element.setAttribute("role", "switch")
  element.setAttribute("aria-checked", "false")

  val selected: Var[Boolean] = Var(false)
  val disabled: Var[Boolean] = Var(false)

  element.innerHTML =
    """<div class="mdc-switch__track"></div>
      |<div class="mdc-switch__handle-track">
      |  <div class="mdc-switch__handle">
      |    <div class="mdc-switch__shadow">
      |      <div class="mdc-elevation-overlay"></div>
      |    </div>
      |    <div class="mdc-switch__ripple"></div>
      |    <div class="mdc-switch__icons">
      |      <svg class="mdc-switch__icon mdc-switch__icon--on" viewBox="0 0 24 24">
      |        <path d="M19.69,5.23L8.96,15.96l-4.23-4.23L2.96,13.5l6,6L21.46,7L19.69,5.23z" />
      |      </svg>
      |      <svg class="mdc-switch__icon mdc-switch__icon--off" viewBox="0 0 24 24">
      |        <path d="M20 13H4v-2h16v2z" />
      |      </svg>
      |    </div>
      |  </div>
      |</div>""".stripMargin

  private val adapter: MDCSwitchImplementation =
    MaterialComponents.verified(MDCSwitch.attachTo(element))

  private var updating = false

  selected.attach { v =>
    if (!updating) {
      adapter.selected = v
      element.setAttribute("aria-checked", v.toString)
    }
  }

  disabled.attach { v =>
    adapter.disabled = v
  }

  element.addEventListener("click", (_: org.scalajs.dom.Event) => {
    updating = true
    selected @= adapter.selected
    element.setAttribute("aria-checked", adapter.selected.toString)
    updating = false
  })
}

@js.native
@JSGlobal("mdc.switchControl.MDCSwitch")
object MDCSwitch extends js.Object {
  def attachTo(element: html.Element): MDCSwitchImplementation = js.native
}
