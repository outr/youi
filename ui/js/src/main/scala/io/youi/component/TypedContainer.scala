package io.youi.component

import io.youi.layout.Layout
import io.youi.theme.ContainerTheme
import reactify.Var

class TypedContainer[C <: Component] extends AbstractContainer with ContainerTheme {
  override type Child = C

  override def `type`: String = "Container"

  override lazy val theme: Var[_ <: ContainerTheme] = Var(TypedContainer)

  override protected def defaultThemeParent = Some(theme)

  def layout: Var[Layout] = layoutManager

  def children: Var[Vector[C]] = childEntries

  init()
}

object TypedContainer extends ContainerTheme