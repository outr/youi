package io.youi.component

import io.youi.layout.Layout
import reactify._

trait AbstractContainer[Child <: Component] extends Component { self =>
  protected lazy val children: Var[Vector[Child]] = Var(Vector.empty)
  protected lazy val layout: Var[Layout] = Var(Layout.None)

  val visibleChildren: Val[Vector[Child]] = Val(children().filter(_.visible()))

  override protected def init(): Unit = {
    super.init()

    if (children.nonEmpty) {
      childrenChanged(Vector.empty, children)
    }
    children.changes {
      case (oldValue, newValue) => {
        val removed = oldValue.collect {
          case c if !newValue.contains(c) => c
        }
        val added = newValue.collect {
          case c if !oldValue.contains(c) => c
        }

        childrenChanged(removed, added)
      }
    }
    visibleChildren.on(childrenChanged(Vector.empty, Vector.empty))

    layout.changes {
      case (oldValue, newValue) => {
        Option(oldValue).foreach(_.disconnect(self))
        Option(newValue).foreach(_.connect(self))
      }
    }

    size.width.and(size.height).on {
      layout.resized(self, size.width, size.height)
    }
  }

  override def update(delta: Double): Unit = {
    super.update(delta)

    children().foreach(_.update(delta))
  }


  override def updateTransform(): Unit = {
    super.updateTransform()

    children().foreach(_.updateTransform())
  }

  protected def childrenChanged(removed: Vector[Child], added: Vector[Child]): Unit = {
    removed.foreach(_.parent := None)
    added.foreach(_.parent := Some(self))

    layout.childrenChanged(self, removed, added)
  }

  override protected def childComponents: Vector[Component] = children()
}