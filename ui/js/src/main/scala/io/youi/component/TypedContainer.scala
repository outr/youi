package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.theme.ContainerTheme
import org.scalajs.dom.html
import reactify.Var

class TypedContainer[Child <: Component](element: html.Element = HTMLComponent.create[html.Div]("div")) extends HTMLContainer[Child](element) {
  override lazy val theme: Var[_ <: ContainerTheme] = Var(Container)
  override def `type`: String = "TypedContainer"
}

object TypedContainer extends ContainerTheme