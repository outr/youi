package io.youi.component

import io.youi.WidgetContainer
import io.youi.drawable.Context
import io.youi.event.HitResult
import io.youi.spatial.Point
import io.youi.theme.AbstractContainerTheme
import reactify._

import scala.annotation.tailrec

trait AbstractContainer extends Component with AbstractContainerTheme with WidgetContainer {
  override type Child <: Component

  override lazy val theme: Var[_ <: AbstractContainerTheme] = Var(AbstractContainer)
  override protected lazy val childEntries: Var[Vector[Child]] = prop(Vector.empty, updatesTransform = true)
  private val childModified = Val(if (childEntries.nonEmpty) childEntries().map(_.modified()).max else 0L)

  override protected def defaultThemeParent = Some(theme)

  override protected def init(): Unit = {
    super.init()

    updateMeasured(
      width = if (childEntries().nonEmpty) childEntries().map(_.position.right()).max else 0.0,
      height = if (childEntries().nonEmpty) childEntries().map(_.position.bottom()).max else 0.0
    )
    childModified.attach(modified := _)
  }

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

  override def hitTest(global: Point): HitResult = if (interactive() && visible()) {
    val children = childEntries()
    val lastIndex = children.length - 1
    val childResult = if (lastIndex >= 0) {
      childHitTest(global, lastIndex, children)
    } else {
      HitResult.Miss
    }
    childResult match {
      case HitResult.Miss => super.hitTest(global)
      case result => result
    }
  } else {
    HitResult.Miss
  }

  @tailrec
  private def childHitTest(global: Point, index: Int, children: Vector[Child]): HitResult = {
    val child = children(index)
    child.hitTest(global) match {
      case result: HitResult.Hit => result
      case HitResult.Miss if index == 0 => HitResult.Miss
      case HitResult.Miss => childHitTest(global, index - 1, children)
    }
  }
}

object AbstractContainer extends AbstractContainerTheme {
  def children(container: AbstractContainer): Val[Vector[Component]] = container.childEntries.asInstanceOf[Val[Vector[Component]]]
}

object Contained {
  def apply[C <: Component](children: C*): AbstractContainer = new AbstractContainer {
    override type Child = C

    override def `type`: String = "Contained"

    childEntries := children.toVector
  }
}