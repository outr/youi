package youi.material

import youi.component.Component
import youi.component.support.ContainerSupport
import youi.component.types.Prop
import youi.dom
import youi.dom._

class MDCCard extends Component(dom.create.div) with ContainerSupport {
  classes := List("mdc-card")

  val outlined: Prop[Boolean] = classes.toggle("mdc-card--outlined")

  def this(isOutlined: Boolean) = {
    this()
    this.outlined @= isOutlined
  }
}
