package io.youi.component

import io.youi.layout.Layout
import io.youi.theme.ContainerTheme
import reactify.Var

class TypedContainer[Child <: Component] extends AbstractContainer[Child] {
  override lazy val theme: Var[_ <: ContainerTheme] = Var(Container)
  override def `type`: String = "TypedContainer"

  override lazy val children: Var[Vector[Child]] = Var(Vector.empty)
  override lazy val layout: Var[Layout] = Var(Layout.None)
}

object TypedContainer extends ContainerTheme