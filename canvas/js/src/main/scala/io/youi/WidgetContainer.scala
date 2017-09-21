package io.youi

import io.youi.layout.Layout
import reactify._

trait WidgetContainer extends Widget { self =>
  type Child <: Widget

  protected def childEntries: Val[Vector[Child]]
  protected val layoutManager: Var[Layout] = Var(Layout.None)

  childEntries.changes(new ChangeObserver[Vector[Child]] {
    override def change(oldValue: Vector[Child], newValue: Vector[Child]): Unit = {
      val removed = oldValue.collect {
        case c: Widget if !newValue.contains(c) => c
      }
      val added = newValue.collect {
        case c: Widget if !oldValue.contains(c) => c
      }

      removed.foreach(_.parentWidget.asInstanceOf[Var[Option[WidgetContainer]]] := None)
      added.foreach(_.parentWidget.asInstanceOf[Var[Option[WidgetContainer]]] := Some(self))

      layoutManager.childrenChanged(self, removed, added)

      invalidate()
    }
  })

  layoutManager.changes(new ChangeObserver[Layout] {
    override def change(oldValue: Layout, newValue: Layout): Unit = synchronized {
      oldValue.disconnect(self)
      newValue.connect(self)
    }
  })

  size.width.and(size.height).on {
    layoutManager.resized(self, size.width, size.height)
  }
}

object WidgetContainer {
  def children(container: WidgetContainer): Val[Vector[Widget]] = container.childEntries.asInstanceOf[Val[Vector[Widget]]]
}