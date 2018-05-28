package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.theme.ContainerTheme
import org.scalajs.dom.html

class TypedContainer[Child <: Component](element: html.Element = HTMLComponent.create[html.Div]("div")) extends HTMLContainer[Child](element) {
  parentTheme := Some(TypedContainer)

  override def componentType: String = "TypedContainer"
}

object TypedContainer extends ContainerTheme