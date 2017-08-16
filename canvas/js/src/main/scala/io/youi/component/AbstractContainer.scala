package io.youi.component

import io.youi.Context
import io.youi.theme.AbstractContainerTheme
import reactify._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

trait AbstractContainer extends Component { self =>
  type Child <: Component

  override lazy val theme: Var[_ <: AbstractContainerTheme] = Var(AbstractContainer)
  protected val childEntries: Var[Vector[Child]] = prop(Vector.empty, updatesTransform = true)

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
    }
  })

  size.measured.width := (if (childEntries().nonEmpty) childEntries().map(_.position.right()).max else 0.0)
  size.measured.height := (if (childEntries().nonEmpty) childEntries().map(_.position.bottom()).max else 0.0)
  childEntries.on(invalidate())

  override def update(delta: Double): Unit = {
    super.update(delta)

    childEntries().foreach(_.update(delta))
  }

  override def draw(context: Context): Future[Unit] = super.draw(context).flatMap { _ =>
    // Draw cached canvases from each child
    childEntries.foreach { child =>
      context.transform(child)
      context.draw(child)
    }

    Future.successful(())
  }
}

object AbstractContainer extends AbstractContainerTheme