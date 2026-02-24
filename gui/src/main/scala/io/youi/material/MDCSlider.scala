package io.youi.material

import io.youi.component.Component
import io.youi.dom
import io.youi.dom._
import io.youi.material.impl.MDCSliderImplementation
import org.scalajs.dom.html
import reactify.{Channel, Var}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCSlider extends Component(dom.create.div) {
  classes := List("mdc-slider")

  val value: Var[Double] = Var(50.0)
  val min: Var[Double] = Var(0.0)
  val max: Var[Double] = Var(100.0)
  val step: Var[Double] = Var(1.0)
  val disabled: Var[Boolean] = Var(false)
  val onChange: Channel[Double] = Channel[Double]

  private object elements {
    val input: html.Input = {
      val inp = dom.create.input
      inp.addClasses("mdc-slider__input")
      inp.`type` = "range"
      inp.setAttribute("min", MDCSlider.this.min().toString)
      inp.setAttribute("max", MDCSlider.this.max().toString)
      inp.setAttribute("value", MDCSlider.this.value().toString)
      inp.setAttribute("step", MDCSlider.this.step().toString)
      inp.setAttribute("aria-label", "Slider")
      inp
    }
  }

  element.innerHTML =
    """<div class="mdc-slider__track">
      |  <div class="mdc-slider__track--inactive"></div>
      |  <div class="mdc-slider__track--active">
      |    <div class="mdc-slider__track--active_fill"></div>
      |  </div>
      |</div>
      |<div class="mdc-slider__thumb">
      |  <div class="mdc-slider__thumb-knob"></div>
      |</div>""".stripMargin

  element.insertBefore(elements.input, element.firstChild)

  private val adapter: MDCSliderImplementation =
    MaterialComponents.verified(MDCSlider.attachTo(element))

  private var updating = false

  value.attach { v =>
    if (!updating) {
      adapter.setValue(v)
    }
  }

  min.attach { v =>
    elements.input.setAttribute("min", v.toString)
    adapter.layout()
  }
  max.attach { v =>
    elements.input.setAttribute("max", v.toString)
    adapter.layout()
  }
  step.attach { v =>
    elements.input.setAttribute("step", v.toString)
    adapter.layout()
  }
  disabled.attach(adapter.disabled_=)

  element.addEventListener("MDCSlider:change", (_: org.scalajs.dom.Event) => {
    updating = true
    val v = adapter.getValue()
    value @= v
    onChange @= v
    updating = false
  })

  element.addEventListener("MDCSlider:input", (_: org.scalajs.dom.Event) => {
    updating = true
    value @= adapter.getValue()
    updating = false
  })
}

@js.native
@JSGlobal("mdc.slider.MDCSlider")
object MDCSlider extends js.Object {
  def attachTo(element: html.Element): MDCSliderImplementation = js.native
}
