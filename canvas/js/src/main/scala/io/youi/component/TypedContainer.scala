package io.youi.component

import io.youi.theme.ContainerTheme
import reactify.Var

class TypedContainer[T <: Component] extends AbstractContainer {
  override type Child = T

  override lazy val theme: Var[_ <: ContainerTheme] = Var(Container)

  def children: Var[Vector[T]] = childEntries
}