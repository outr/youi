package io.youi.material

import io.youi.component.Component
import io.youi.dom
import io.youi.dom._
import io.youi.material.impl.{MDCRipple, MDCRippleImplementation}
import org.scalajs.dom.html
import reactify.Var

class MDCButton extends Component(dom.create.button) {
  classes := Set("mdc-button")

  val label: Var[String] = Var("")

  private object elements {
    val ripple: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-button__ripple")
      div
    }
    val label: html.Span = {
      val span = dom.create.span
      span.addClasses("mdc-button__label")
      MDCButton.this.label.attach(span.innerHTML_=)
      span
    }
  }

  element.appendChild(elements.ripple)
  element.appendChild(elements.label)

  private val adapter: MDCRippleImplementation = MaterialComponents.verified(MDCRipple.attachTo(element))
}