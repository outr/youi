package org.hyperscala.ui.history

import org.powerscala.property.Property

/**
 * @author Matt Hicks <matt@outr.com>
 */
trait HistoryEntry {
  def description: String
  def time: Long
  def undo(): Unit
  def redo(): Unit
  def mergeWithPrevious(previous: HistoryEntry): Option[HistoryEntry]
}

object HistoryEntry {
  def apply(description: String, time: Long = System.currentTimeMillis())(undo: => Unit)(redo: => Unit) = {
    FunctionalHistoryEntry(description, () => undo, () => redo, time)
  }

  def propertyChange[T](description: String,
                        property: Property[T],
                        previousValue: T,
                        newValue: T,
                        time: Long = System.currentTimeMillis()) = {
    apply(description, time) {
      property := previousValue
    } {
      property := newValue
    }
  }
}

case class FunctionalHistoryEntry(description: String,
                                  undoFunction: () => Unit,
                                  redoFunction: () => Unit,
                                  time: Long = System.currentTimeMillis()) extends HistoryEntry {
  def undo() = undoFunction()

  def redo() = redoFunction()

  def mergeWithPrevious(previous: HistoryEntry) = None
}