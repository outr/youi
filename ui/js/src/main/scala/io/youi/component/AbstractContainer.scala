package io.youi.component

import io.youi.layout.Layout
import reactify._

trait AbstractContainer[Child <: Component] extends Component { self =>
  protected lazy val children: Var[Vector[Child]] = Var(Vector.empty)
  protected lazy val layout: Var[Layout] = Var(Layout.None)

  override protected def init(): Unit = {
    super.init()

    if (children.nonEmpty) {
      childrenChanged(Vector.empty, children)
    }
    children.changes(new ChangeObserver[Vector[Child]] {
      override def change(oldValue: Vector[Child], newValue: Vector[Child]): Unit = {
        val removed = oldValue.collect {
          case c if !newValue.contains(c) => c
        }
        val added = newValue.collect {
          case c if !oldValue.contains(c) => c
        }

        childrenChanged(removed, added)
      }
    })

    layout.changes(new ChangeObserver[Layout] {
      override def change(oldValue: Layout, newValue: Layout): Unit = synchronized {
        oldValue.disconnect(self)
        newValue.connect(self)
      }
    })

    size.width.and(size.height).on {
      layout.resized(self, size.width, size.height)
    }
  }

  protected def childrenChanged(removed: Vector[Child], added: Vector[Child]): Unit = {
    removed.foreach(_.parent := None)
    added.foreach(_.parent := Some(self))

    layout.childrenChanged(self, removed, added)
  }

  override protected def childComponents: Vector[Component] = children()
}