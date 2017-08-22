package io.youi.component

import io.youi.layout.Layout
import io.youi.theme.RendererTheme
import reactify.Var

class Container extends AbstractContainer with RendererTheme {
  override type Child = Component

  override lazy val theme: Var[_ <: RendererTheme] = Var(Container)

  override protected def defaultThemeParent = Some(theme)

  def layout: Var[Layout] = layoutManager

  def children: Var[Vector[Component]] = childEntries
}

object Container extends RendererTheme