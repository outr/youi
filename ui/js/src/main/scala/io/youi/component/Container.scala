package io.youi.component

import io.youi.layout.Layout
import io.youi.theme.ContainerTheme
import reactify.Var

class Container extends AbstractContainer with ContainerTheme {
  override type Child = Component

  override def `type`: String = "Container"

  override lazy val theme: Var[_ <: ContainerTheme] = Var(Container)

  init()

  override protected def defaultThemeParent = Some(theme)

  def layout: Var[Layout] = layoutManager

  def children: Var[Vector[Component]] = childEntries
}

object Container extends ContainerTheme