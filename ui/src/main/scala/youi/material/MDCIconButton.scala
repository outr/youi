package youi.material

import youi.component.Component
import youi.dom
import youi.material.impl.{MDCRipple, MDCRippleImplementation}
import reactify.Var

class MDCIconButton extends Component(dom.create.button) {
  classes := List("mdc-icon-button", "material-icons")

  def this(icon: MaterialIcon) = {
    this()
    this.icon @= icon
  }

  val icon: Var[MaterialIcon] = Var(Material.Icons.Alert.Warning)

  icon.attachAndFire(i => element.innerHTML = i.name)

  private val adapter: MDCRippleImplementation = MaterialComponents.verified(MDCRipple.attachTo(element))
  adapter.unbounded = true
}