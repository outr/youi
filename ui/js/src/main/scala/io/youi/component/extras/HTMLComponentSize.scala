package io.youi.component.extras

class HTMLComponentSize(override protected val component: Component) extends ComponentSize with StringifyImplicits {
  private def e: html.Element = HTMLComponent.element(component)

  width.attach { value =>
    if (component != ui) e.style.width = s"${value.ceil}px"
  }
  height.attach { value =>
    if (component != ui) e.style.height = s"${value.ceil}px"
  }

  object view {
    lazy val width: Val[Double] = Var(e.clientWidth.toDouble)
    lazy val height: Val[Double] = Var(e.clientHeight.toDouble)
  }

  object scroll {
    lazy val width: Val[Double] = Var(e.getBoundingClientRect().width)
    lazy val height: Val[Double] = Var(e.getBoundingClientRect().height)
  }
}