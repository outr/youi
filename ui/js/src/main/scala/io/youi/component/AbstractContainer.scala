package io.youi.component

import io.youi.WidgetContainer
import io.youi.drawable.Context
import io.youi.theme.AbstractContainerTheme
import reactify._

trait AbstractContainer extends Component with AbstractContainerTheme with WidgetContainer {
  override type Child <: Component

  override lazy val theme: Var[_ <: AbstractContainerTheme] = Var(AbstractContainer)
  override protected lazy val childEntries: Var[Vector[Child]] = prop(Vector.empty, updatesTransform = true)

  override protected def defaultThemeParent = Some(theme)

  private val childModified = Val(if (childEntries.nonEmpty) childEntries().map(_.modified()).max else 0L)
  childModified.attach(modified := _)

  override protected def drawInternal(context: Context): Unit = {
    childEntries.foreach { child =>
      if (child.visible()) {
        child.draw(context, 0.0, 0.0)
      }
    }
  }

  override def update(delta: Double): Unit = {
    super.update(delta)

    childEntries.foreach(_.update(delta))
  }
}

object AbstractContainer extends AbstractContainerTheme {
  def children(container: AbstractContainer): Val[Vector[Component]] = container.childEntries.asInstanceOf[Val[Vector[Component]]]
}

object Contained {
  def apply[C <: Component](children: C*): AbstractContainer = new AbstractContainer {
    override type Child = C

    childEntries := children.toVector
  }
}