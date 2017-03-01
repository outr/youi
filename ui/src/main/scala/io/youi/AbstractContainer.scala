package io.youi

import reactify._

trait AbstractContainer[C <: AbstractComponent] {
  val children: Var[Vector[C]] = Var[Vector[C]](Vector.empty)

  children.changes(new ChangeListener[Vector[C]] {
    override def change(oldValue: Vector[C], newValue: Vector[C]): Unit = {
      var modifiedOld = oldValue

      // Remove values that existed previously that are not available in the new version
      oldValue.foreach { c =>
        if (!newValue.contains(c)) {
          modifiedOld = modifiedOld.filterNot(_ == c)
          remove(c)
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
          }
          previous = Some(c)
        }
      }
    }
  })

  protected def remove(c: C): Unit

  protected def addAfter(c: C, previous: Option[C]): Unit
}
