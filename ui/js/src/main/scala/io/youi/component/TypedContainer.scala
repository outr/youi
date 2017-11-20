package io.youi.component

import io.youi.component.extras.HTMLComponent
import io.youi.layout.Layout
import io.youi.theme.ContainerTheme
import org.scalajs.dom.html
import reactify.Var

class TypedContainer[Child <: Component](override val element: html.Element = HTMLComponent.create[html.Div]("div")) extends AbstractContainer[Child] with HTMLComponent {
  override lazy val theme: Var[_ <: ContainerTheme] = Var(Container)
  override def `type`: String = "TypedContainer"

  override lazy val children: Var[Vector[Child]] = Var(Vector.empty)
  override lazy val layout: Var[Layout] = Var(Layout.None)
}

object TypedContainer extends ContainerTheme