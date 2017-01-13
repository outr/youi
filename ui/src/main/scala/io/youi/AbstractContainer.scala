package io.youi

import com.outr.reactify._

trait AbstractContainer[C <: AbstractComponent] {
  object children extends Var[Vector[C]](Nil, Vector.empty) {
    def +=(child: C): Unit = this := this() :+ child
    def -=(child: C): Unit = this := this().filterNot(_ eq child)
  }

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
