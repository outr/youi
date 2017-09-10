package io.youi.hypertext

import io.youi.hypertext.layout.Layout
import reactify._

trait AbstractContainer[C <: AbstractComponent] extends AbstractComponent {
  val layoutManager: Var[Option[Layout]] = Var(None)
  val children: Var[Vector[C]] = Var[Vector[C]](Vector.empty)

  layoutManager.changes(new ChangeObserver[Option[Layout]] {
    override def change(oldValue: Option[Layout], newValue: Option[Layout]): Unit = synchronized {
      oldValue.foreach(l => Layout.disconnect[C](AbstractContainer.this, l))
      newValue.foreach(l => Layout.connect[C](AbstractContainer.this, l))
    }
  })

  children.changes(new ChangeObserver[Vector[C]] {
    override def change(oldValue: Vector[C], newValue: Vector[C]): Unit = {
      var modifiedOld = oldValue

      // Remove values that existed previously that are not available in the new version
      oldValue.foreach { c =>
        if (!newValue.contains(c)) {
          modifiedOld = modifiedOld.filterNot(_ == c)
          remove(c)
          c.parent.asInstanceOf[Var[Option[AbstractComponent]]] := None
        }
      }
      // Iterate over new and deal with changes
      var previous: Option[C] = None
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
            c.parent.asInstanceOf[Var[Option[AbstractComponent]]] := Some(AbstractContainer.this)
          }
          previous = Some(c)
        }
      }
    }
  })

  protected def remove(c: C): Unit

  protected def addAfter(c: C, previous: Option[C]): Unit

  override def update(delta: Double): Unit = {
    super.update(delta)

    children().foreach(_.update(delta))
  }
}