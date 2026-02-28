package youi.material

import youi.Unique
import youi.component.Component
import youi.dom
import youi.dom._
import youi.material.impl.MDCCheckboxImplementation
import org.scalajs.dom.html
import reactify.Var

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCCheckbox extends Component(dom.create.div) {
  classes := List("mdc-checkbox")

  val inputId: String = Unique()

  val checked: Var[Boolean] = Var(false)
  val indeterminate: Var[Boolean] = Var(false)
  val disabled: Var[Boolean] = Var(false)
  val value: Var[String] = Var("")

  private object elements {
    val input: html.Input = {
      val inp = dom.create.input
      inp.id = inputId
      inp.`type` = "checkbox"
      inp.addClasses("mdc-checkbox__native-control")
      inp
    }
  }

  element.innerHTML =
    """<div class="mdc-checkbox__background">
      |  <svg class="mdc-checkbox__checkmark" viewBox="0 0 24 24">
      |    <path class="mdc-checkbox__checkmark-path" fill="none" d="M1.73,12.91 8.1,19.28 22.79,4.59"/>
      |  </svg>
      |  <div class="mdc-checkbox__mixedmark"></div>
      |</div>
      |<div class="mdc-checkbox__ripple"></div>""".stripMargin

  // Insert input before the background div
  element.insertBefore(elements.input, element.firstChild)

  private val adapter: MDCCheckboxImplementation =
    MaterialComponents.verified(MDCCheckbox.attachTo(element))

  private var updating = false

  checked.attach { v =>
    if (!updating) {
      adapter.checked = v
    }
  }
  indeterminate.attach { v =>
    if (!updating) {
      adapter.indeterminate = v
    }
  }
  disabled.attach(adapter.disabled_=)
  value.attach(adapter.value_=)

  elements.input.addEventListener("change", (_: org.scalajs.dom.Event) => {
    updating = true
    checked @= adapter.checked
    indeterminate @= adapter.indeterminate
    updating = false
  })
}

@js.native
@JSGlobal("mdc.checkbox.MDCCheckbox")
object MDCCheckbox extends js.Object {
  def attachTo(element: html.Element): MDCCheckboxImplementation = js.native
}
