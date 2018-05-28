package io.youi.component.extras

import io.youi.component.Component
import io.youi.theme.StringifyImplicits
import org.scalajs.dom.html

class HTMLComponentSize(override protected val component: Component) extends ComponentSize with StringifyImplicits {
//  private lazy val ceilFunction = (d: Double) => math.ceil(d)

//  override lazy val width: Var[Double] = component.style[Double]("width", 0.0, StyleConnect.style[Double](ceilFunction))
//  override lazy val height: Var[Double] = component.style[Double]("height", 0.0, StyleConnect.style[Double](ceilFunction))
  private def e: html.Element = HTMLComponent.element(component)

  width.attach { value =>
    e.style.width = s"${value.ceil}px"
  }
  height.attach { value =>
    e.style.height = s"${value.ceil}px"
  }
}