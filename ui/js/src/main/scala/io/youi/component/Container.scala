package io.youi.component

import io.youi.layout.Layout
import io.youi.theme.ContainerTheme
import reactify.Var

class Container extends AbstractContainer[Component] {
  override lazy val theme: Var[_ <: ContainerTheme] = Var(Container)
  override def `type`: String = "Container"

  override lazy val children: Var[Vector[Component]] = Var(Vector.empty)
  override lazy val layout: Var[Layout] = Var(Layout.None)
}

object Container extends ContainerTheme