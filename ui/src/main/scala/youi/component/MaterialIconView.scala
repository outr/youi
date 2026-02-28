package youi.component

import youi.component.support.{ContentSupport, FontSupport}
import youi.dom
import youi.material.{Material, MaterialIcon}
import org.scalajs.dom.html
import reactify.Var

class MaterialIconView(element: html.Element = dom.create.i) extends Component(element) with FontSupport with ContentSupport {
  lazy val icon: Var[MaterialIcon] = Var(Material.Icons.Empty)

  classes += "material-icons"

  font @= Material

  icon.attach { icon =>
    content @= icon.name
  }
}