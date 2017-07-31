package io.youi.component

import com.outr.pixijs.PIXI
import io.youi.component.layout.Layout
import io.youi.theme.AbstractContainerTheme
import reactify.{ChangeListener, Var}

class AbstractContainer extends Component { self =>
  type Child <: Component

  override protected[component] lazy val instance: PIXI.Container = new PIXI.Container()

  override lazy val theme: Var[_ <: AbstractContainerTheme] = Var(AbstractContainer)

  protected val layoutManager: Var[Layout] = Var(Layout.None)
  protected val childEntries: Var[Vector[Child]] = prop(Vector.empty, updatesTransform = true)

  layoutManager.changes(new ChangeListener[Layout] {
    override def change(oldValue: Layout, newValue: Layout): Unit = synchronized {
      Layout.disconnect(self, oldValue)
      Layout.connect(self, newValue)
    }
  })
  childEntries.changes(new ChangeListener[Vector[Child]] {
    override def change(oldValue: Vector[Child], newValue: Vector[Child]): Unit = {
      var modifiedOld = oldValue

      // Remove values that existed previously that are not available in the new version
      oldValue.foreach { c =>
        if (!newValue.contains(c)) {
          modifiedOld = modifiedOld.filterNot(_ == c)
          remove(c)
          c.parent.asInstanceOf[Var[Option[Container]]] := None
        }
      }
      // Iterate over new and deal with changes
      var previous: Option[Component] = None
      newValue.zipWithIndex.foreach {
        case (c, index) => {
          val shouldAdd = if (modifiedOld.contains(c)) {
            if (modifiedOld.indexOf(c) == index) {
              false
            } else {
              remove(c)
              true
            }
          } else {
            true
          }
          if (shouldAdd) {
            addAfter(c, previous)
            c.parent.asInstanceOf[Var[Option[AbstractContainer]]] := Some(AbstractContainer.this)
          }
          previous = Some(c)
        }
      }
    }
  })

  size.measured.width := (if (childEntries().nonEmpty) childEntries().map(_.position.right()).max else 0.0)
  size.measured.height := (if (childEntries().nonEmpty) childEntries().map(_.position.bottom()).max else 0.0)

  protected def addAfter(component: Component, previous: Option[Component]): Unit = previous match {
    case Some(p) => {
      val index = instance.getChildIndex(p.instance)
      instance.addChildAt(component.instance, index + 1)
    }
    case None => instance.addChild(component.instance)
  }

  protected def remove(component: Component): Unit = instance.removeChild(component.instance)

  override def update(delta: Double): Unit = {
    super.update(delta)

    childEntries().foreach(_.update(delta))
  }
}

object AbstractContainer extends AbstractContainerTheme