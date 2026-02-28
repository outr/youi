package youi.material

import youi.Unique
import youi.component.Component
import youi.dom
import youi.dom._
import youi.material.impl.MDCRadioImplementation
import org.scalajs.dom.html
import reactify.Var

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCRadio extends Component(dom.create.div) {
  classes := List("mdc-radio")

  val inputId: String = Unique()

  val checked: Var[Boolean] = Var(false)
  val disabled: Var[Boolean] = Var(false)
  val value: Var[String] = Var("")
  val name: Var[String] = Var("")

  private object elements {
    val input: html.Input = {
      val inp = dom.create.input
      inp.id = inputId
      inp.`type` = "radio"
      inp.addClasses("mdc-radio__native-control")
      MDCRadio.this.name.attachAndFire(inp.name_=)
      MDCRadio.this.value.attachAndFire(inp.value_=)
      inp
    }
  }

  element.innerHTML =
    """<div class="mdc-radio__background">
      |  <div class="mdc-radio__outer-circle"></div>
      |  <div class="mdc-radio__inner-circle"></div>
      |</div>
      |<div class="mdc-radio__ripple"></div>""".stripMargin

  element.insertBefore(elements.input, element.firstChild)

  private val adapter: MDCRadioImplementation =
    MaterialComponents.verified(MDCRadio.attachTo(element))

  private var updating = false

  checked.attach { v =>
    if (!updating) {
      adapter.checked = v
    }
  }
  disabled.attach(adapter.disabled_=)

  elements.input.addEventListener("change", (_: org.scalajs.dom.Event) => {
    updating = true
    checked @= adapter.checked
    updating = false
  })
}

@js.native
@JSGlobal("mdc.radio.MDCRadio")
object MDCRadio extends js.Object {
  def attachTo(element: html.Element): MDCRadioImplementation = js.native
}
