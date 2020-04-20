package io.youi.material

import io.youi._
import io.youi.component.Component
import io.youi.material.impl.{MDCLinearProgress, MDCLinearProgressImplementation}
import reactify.Var

class MDCProgressBar extends Component(dom.create.div) {
  val label: Var[String] = Var("")

  classes += "mdc-linear-progress"
  element.setAttribute("role", "progressbar")
  label.attach(s => element.setAttribute("aria-label", s))
  element.setAttribute("aria-valuemin", "0")
  element.setAttribute("aria-valuemax", "1")
  element.setAttribute("aria-valuenow", "0")

  element.innerHTML =
    """<div class="mdc-linear-progress__buffering-dots"></div>
      |  <div class="mdc-linear-progress__buffer"></div>
      |  <div class="mdc-linear-progress__bar mdc-linear-progress__primary-bar">
      |    <span class="mdc-linear-progress__bar-inner"></span>
      |  </div>
      |  <div class="mdc-linear-progress__bar mdc-linear-progress__secondary-bar">
      |    <span class="mdc-linear-progress__bar-inner"></span>
      |  </div>""".stripMargin

  private val adapter: MDCLinearProgressImplementation = MaterialComponents.verified(MDCLinearProgress.attachTo(element))

  val value: Var[Double] = Var(0.0)
  val buffer: Var[Double] = Var(0.0)
  val reverse: Var[Boolean] = Var(false)

  value.attach { d =>
    adapter.`foundation_`.setDeterminate(d >= 0.0)
    adapter.`foundation_`.setProgress(d)
  }
  buffer.attach(adapter.`foundation_`.setBuffer)
  reverse.attach(adapter.`foundation_`.setReverse)
}