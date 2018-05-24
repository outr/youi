package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.theme.ContainerTheme
import org.scalajs.dom.html

class Container(element: html.Element = HTMLComponent.create[html.Div]("div")) extends HTMLContainer[Component](element) with ContainerTheme {
  parentTheme := Some(Container)

  override def componentType: String = "Container"
}

object Container extends ContainerTheme {
  def apply(children: Component*): Container = {
    val container = new Container
    container.children := children.toVector
    container
  }
}