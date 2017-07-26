package io.youi.component

import io.youi.theme.ContainerTheme
import reactify.Var

class Container extends AbstractContainer {
  override type Child = Component

  override lazy val theme: Var[_ <: ContainerTheme] = Var(Container)

  def children: Var[Vector[Component]] = childEntries
}

object Container extends ContainerTheme