package youi.material

import youi.component.Component
import youi.dom
import youi.dom._
import youi.material.impl.MDCIconButtonToggleImplementation
import org.scalajs.dom.html
import reactify.Var

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

class MDCIconButtonToggle extends Component(dom.create.button) {
  classes := List("mdc-icon-button")
  element.setAttribute("aria-pressed", "false")

  val on: Var[MaterialIcon] = Var(Material.Icons.Alert.ErrorOutline)
  val off: Var[MaterialIcon] = Var(Material.Icons.Alert.Error)

  private object elements {
    val on: html.Element = dom.create[html.Element]("i")
    val off: html.Element = dom.create[html.Element]("i")

    on.addClasses("material-icons", "mdc-icon-button__icon", "mdc-icon-button__icon--on")
    MDCIconButtonToggle.this.on.attachAndFire(i => on.innerHTML = i.name)

    off.addClasses("material-icons", "mdc-icon-button__icon")
    MDCIconButtonToggle.this.off.attachAndFire(i => off.innerHTML = i.name)
  }

  element.appendChild(elements.on)
  element.appendChild(elements.off)

  private val adapter: MDCIconButtonToggleImplementation = MaterialComponents.verified(MDCIconButtonToggle.attachTo(element))
}

@js.native
@JSGlobal("mdc.iconButton.MDCIconButtonToggle")
object MDCIconButtonToggle extends js.Object {
  def attachTo(element: html.Element): MDCIconButtonToggleImplementation = js.native
}