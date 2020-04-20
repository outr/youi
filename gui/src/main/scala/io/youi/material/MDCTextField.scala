package io.youi.material

import io.youi.component.Component
import io.youi.component.types.Prop
import io.youi.dom._
import io.youi.material.impl.MDCTextFieldImplementation
import io.youi.{Unique, dom}
import org.scalajs.dom.html
import reactify.Var

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCTextField extends Component(dom.create.div) {
  classes := Set("mdc-text-field")

  val label: Var[String] = Var("")
  val value: Var[String] = Var("")
  val fullWidth: Prop[Boolean] = classes.toggle("mdc-text-field--fullwidth")
  val outlined: Prop[Boolean] = classes.toggle("mdc-text-field--outlined")

  private object elements {
    val input: html.Input = {
      val input = dom.create.input
      input.id = Unique()
      input.addClasses("mdc-text-field__input")
      MDCTextField.this.value.attach(input.value_=)
      // TODO: Support change events
      input
    }
    val lineRipple: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-line-ripple")
      div
    }
    val label: html.Label = {
      val label = dom.create.label
      label.addClasses("mdc-floating-label")
      label.htmlFor = input.id
      MDCTextField.this.label.attach(label.innerHTML_=)
      label
    }
  }

  element.appendChild(elements.input)
  element.appendChild(elements.lineRipple)
  element.appendChild(elements.label)

  private val adapter: MDCTextFieldImplementation = {
    assert(MaterialComponents.loaded.isCompleted, "MaterialComponents is not loaded!")
    MDCTextField.attachTo(element)
  }

  def shakeLabel(): Unit = adapter.getLabelAdapterMethods_().shakeLabel(true)
}

@js.native
@JSGlobal("mdc.textField.MDCTextField")
object MDCTextField extends js.Object {
  def attachTo(element: html.Element): MDCTextFieldImplementation = js.native
}