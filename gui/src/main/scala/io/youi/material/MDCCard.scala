package io.youi.material

import io.youi.component.Component
import io.youi.component.support.ContainerSupport
import io.youi.component.types.Prop
import io.youi.dom
import io.youi.dom._

class MDCCard extends Component(dom.create.div) with ContainerSupport {
  classes := List("mdc-card")

  val outlined: Prop[Boolean] = classes.toggle("mdc-card--outlined")

  def this(isOutlined: Boolean) = {
    this()
    this.outlined @= isOutlined
  }
}
