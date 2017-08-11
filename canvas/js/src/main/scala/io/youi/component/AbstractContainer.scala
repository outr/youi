package io.youi.component

import io.youi.Context
import io.youi.theme.AbstractContainerTheme
import reactify.Var

trait AbstractContainer extends Component { self =>
  type Child <: Component

  override lazy val theme: Var[_ <: AbstractContainerTheme] = Var(AbstractContainer)
  protected val childEntries: Var[Vector[Child]] = prop(Vector.empty, updatesTransform = true)

  size.measured.width := (if (childEntries().nonEmpty) childEntries().map(_.position.right()).max else 0.0)
  size.measured.height := (if (childEntries().nonEmpty) childEntries().map(_.position.bottom()).max else 0.0)

  override def update(delta: Double): Unit = {
    super.update(delta)

    childEntries().foreach(_.update(delta))
  }

  override def draw(context: Context): Unit = {
    super.draw(context)
    childEntries.foreach { child =>
      context.transform(child.matrix.world)
      context.draw(child)
    }
  }
}

object AbstractContainer extends AbstractContainerTheme