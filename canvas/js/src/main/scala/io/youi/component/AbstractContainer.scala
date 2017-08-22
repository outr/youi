package io.youi.component

import io.youi.Context
import io.youi.event.HitResult
import io.youi.spatial.Point
import io.youi.theme.AbstractContainerTheme
import reactify._

import scala.annotation.tailrec
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait AbstractContainer extends Component with AbstractContainerTheme { self =>
  type Child <: Component

  override lazy val theme: Var[_ <: AbstractContainerTheme] = Var(AbstractContainer)
  protected val childEntries: Var[Vector[Child]] = prop(Vector.empty, updatesTransform = true)

  override protected def defaultThemeParent = Some(theme)

  childEntries.changes(new ChangeListener[Vector[Child]] {
    override def change(oldValue: Vector[Child], newValue: Vector[Child]): Unit = {
      val removed = oldValue.collect {
        case c: Component if !newValue.contains(c) => c
      }
      val added = newValue.collect {
        case c: Component if !oldValue.contains(c) => c
      }

      removed.foreach(_.parent.asInstanceOf[Var[Option[AbstractContainer]]] := None)
      added.foreach(_.parent.asInstanceOf[Var[Option[AbstractContainer]]] := Some(self))

//      layoutManager.childrenChanged(self, removed, added)

      invalidate()
    }
  })

  size.measured.width := (if (childEntries().nonEmpty) childEntries().map(_.position.right()).max else 0.0)
  size.measured.height := (if (childEntries().nonEmpty) childEntries().map(_.position.bottom()).max else 0.0)

  override def update(delta: Double): Unit = {
    super.update(delta)

    childEntries().foreach(_.update(delta))
  }

  override def draw(context: Context): Unit = {
    super.draw(context)

    // Draw cached canvases from each child
    childEntries.foreach { child =>
      if (child.visible()) {
        context.transform(child)
        context.draw(child)
      }
    }
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

object AbstractContainer extends AbstractContainerTheme