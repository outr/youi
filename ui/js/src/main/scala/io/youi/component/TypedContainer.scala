package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.theme.{ContainerTheme, Theme}
import org.scalajs.dom.html

class TypedContainer[Child <: Component](element: html.Element = HTMLComponent.create[html.Div]("div")) extends HTMLContainer[Child](element) {
  override protected def defaultParentTheme: Theme = TypedContainer

  override def componentType: String = "TypedContainer"
}

object TypedContainer extends ContainerTheme {
  override protected def defaultParentTheme: Theme = HTMLContainer
}