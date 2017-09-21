package io.youi.hypertext

import io.youi.WidgetContainer
import reactify._

trait AbstractContainer[C <: AbstractComponent] extends AbstractComponent with WidgetContainer {
  override type Child = C
  override protected[youi] lazy val childEntries: Var[Vector[C]] = Var[Vector[C]](Vector.empty)

  childEntries.changes(new ChangeObserver[Vector[C]] {
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

    childEntries().foreach(_.update(delta))
  }
}