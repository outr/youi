package io.youi.component

class Container(element: html.Element = HTMLComponent.create[html.Div]("div"),
                existing: Boolean = false) extends HTMLContainer[Component](element) with ContainerTheme {
  override protected def defaultParentTheme: Theme = Container

  override def componentType: String = "Container"
}

object Container extends ContainerTheme {
  override protected def defaultParentTheme: Theme = Component

  def apply(children: Component*): Container = {
    val container = new Container
    container.children @= children.toVector
    container
  }

  def existing(id: String, in: html.Element = document.body): Container = {
    new Container(in.byId[html.Element](id), existing = true)
  }
}