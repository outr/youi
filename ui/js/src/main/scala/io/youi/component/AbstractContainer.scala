package io.youi.component

import io.youi.WidgetContainer
import io.youi.theme.AbstractContainerTheme
import reactify._

trait AbstractContainer extends Component with AbstractContainerTheme with WidgetContainer {
  override type Child <: Component

  override lazy val theme: Var[_ <: AbstractContainerTheme] = Var(AbstractContainer)
  override protected lazy val childEntries: Var[Vector[Child]] = prop(Vector.empty, updatesTransform = true)

  override protected def defaultThemeParent = Some(theme)
}

object AbstractContainer extends AbstractContainerTheme {
  def children(container: AbstractContainer): Val[Vector[Component]] = container.childEntries.asInstanceOf[Val[Vector[Component]]]
}