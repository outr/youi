package youi.material

import youi.component.Component
import youi.dom
import youi.dom._
import youi.material.impl.MDCCircularProgressImplementation
import org.scalajs.dom.html
import reactify.Var

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCCircularProgress extends Component(dom.create.div) {
  classes := List("mdc-circular-progress")
  element.style.width = "48px"
  element.style.height = "48px"
  element.setAttribute("role", "progressbar")
  element.setAttribute("aria-valuemin", "0")
  element.setAttribute("aria-valuemax", "1")

  element.innerHTML =
    """<div class="mdc-circular-progress__determinate-container">
      |  <svg class="mdc-circular-progress__determinate-circle-graphic" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
      |    <circle class="mdc-circular-progress__determinate-track" cx="24" cy="24" r="18" stroke-width="4"/>
      |    <circle class="mdc-circular-progress__determinate-circle" cx="24" cy="24" r="18" stroke-dasharray="113.097" stroke-dashoffset="113.097" stroke-width="4"/>
      |  </svg>
      |</div>
      |<div class="mdc-circular-progress__indeterminate-container">
      |  <div class="mdc-circular-progress__spinner-layer">
      |    <div class="mdc-circular-progress__circle-left">
      |      <svg class="mdc-circular-progress__indeterminate-circle-graphic" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
      |        <circle cx="24" cy="24" r="18" stroke-dasharray="113.097" stroke-dashoffset="56.549" stroke-width="4"/>
      |      </svg>
      |    </div>
      |    <div class="mdc-circular-progress__gap-patch">
      |      <svg class="mdc-circular-progress__indeterminate-circle-graphic" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
      |        <circle cx="24" cy="24" r="18" stroke-dasharray="113.097" stroke-dashoffset="56.549" stroke-width="4"/>
      |      </svg>
      |    </div>
      |    <div class="mdc-circular-progress__circle-right">
      |      <svg class="mdc-circular-progress__indeterminate-circle-graphic" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
      |        <circle cx="24" cy="24" r="18" stroke-dasharray="113.097" stroke-dashoffset="56.549" stroke-width="4"/>
      |      </svg>
      |    </div>
      |  </div>
      |</div>""".stripMargin

  private val adapter: MDCCircularProgressImplementation =
    MaterialComponents.verified(MDCCircularProgress.attachTo(element))

  /** Set value >= 0 for determinate progress, negative for indeterminate. */
  val value: Var[Double] = Var(-1.0)

  value.attachAndFire { d =>
    if (d < 0.0) {
      adapter.determinate = false
      adapter.open()
    } else {
      adapter.determinate = true
      adapter.progress = d
      adapter.open()
    }
  }

  def close(): Unit = adapter.close()
}

@js.native
@JSGlobal("mdc.circularProgress.MDCCircularProgress")
object MDCCircularProgress extends js.Object {
  def attachTo(element: html.Element): MDCCircularProgressImplementation = js.native
}
