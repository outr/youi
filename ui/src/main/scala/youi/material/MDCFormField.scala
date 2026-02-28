package youi.material

import youi.component.Component
import youi.dom
import youi.dom._
import youi.material.impl.MDCFormFieldImplementation
import org.scalajs.dom.html
import reactify.Var

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCFormField extends Component(dom.create.div) {
  classes := List("mdc-form-field")

  val labelText: Var[String] = Var("")

  private object elements {
    val label: html.Label = {
      val lbl = dom.create.label
      MDCFormField.this.labelText.attachAndFire(lbl.innerHTML_=)
      lbl
    }
  }

  element.appendChild(elements.label)

  private val adapter: MDCFormFieldImplementation =
    MaterialComponents.verified(MDCFormField.attachTo(element))

  /** Set the input component (e.g., MDCCheckbox or MDCRadio). Inserts the element before the label and links the form field adapter. */
  def setInput(checkbox: MDCCheckbox): Unit = {
    element.insertBefore(checkbox.element, elements.label)
    elements.label.htmlFor = checkbox.inputId
    adapter.input = checkbox.element.querySelector(".mdc-checkbox__native-control")
  }

  def setInput(radio: MDCRadio): Unit = {
    element.insertBefore(radio.element, elements.label)
    elements.label.htmlFor = radio.inputId
    adapter.input = radio.element.querySelector(".mdc-radio__native-control")
  }
}

@js.native
@JSGlobal("mdc.formField.MDCFormField")
object MDCFormField extends js.Object {
  def attachTo(element: html.Element): MDCFormFieldImplementation = js.native
}
