package io.youi.material

import io.youi.Unique
import io.youi.component.Component
import io.youi.dom
import io.youi.dom._
import io.youi.material.impl.MDCTooltipImplementation
import org.scalajs.dom.html
import reactify.Var

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCTooltip extends Component(dom.create.div) {
  private val tooltipId: String = Unique()

  classes := List("mdc-tooltip")
  element.setAttribute("id", tooltipId)
  element.setAttribute("role", "tooltip")
  element.setAttribute("aria-hidden", "true")

  val text: Var[String] = Var("")

  def this(text: String) = {
    this()
    this.text @= text
  }

  private object elements {
    val surface: html.Div = {
      val div = dom.create.div
      div.addClasses("mdc-tooltip__surface", "mdc-tooltip__surface-animation")
      MDCTooltip.this.text.attachAndFire(div.innerHTML_=)
      div
    }
  }

  element.appendChild(elements.surface)

  /** Attach this tooltip to an anchor component. Sets the required aria/data attributes. */
  def attachToAnchor(component: Component): Unit = {
    component.element.setAttribute("aria-describedby", tooltipId)
    component.element.setAttribute("data-tooltip-id", tooltipId)
  }

  private lazy val adapter: MDCTooltipImplementation =
    MaterialComponents.verified(MDCTooltip.attachTo(element))

  def init(): Unit = { val _ = adapter }
  def hide(): Unit = adapter.hide()
}

@js.native
@JSGlobal("mdc.tooltip.MDCTooltip")
object MDCTooltip extends js.Object {
  def attachTo(element: html.Element): MDCTooltipImplementation = js.native
}
