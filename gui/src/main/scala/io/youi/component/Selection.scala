package io.youi.component

import reactify.{Channel, Var}

class Selection[T] {
  val highlighted: Var[Set[T]] = Var(Set.empty)
  val selected: Var[Set[T]] = Var(Set.empty)
  val changed: Channel[Set[T]] = Channel[Set[T]]

  selected.attach(changed @= _)

  def set(item: T): Unit = {
    selected @= Set(item)
  }

  def toggle(item: T): Unit = {
    val current = selected()
    if (current.contains(item)) {
      selected @= current - item
    } else {
      selected @= current + item
    }
  }

  def range(from: T, to: T, all: Seq[T]): Unit = {
    val fromIndex = all.indexOf(from)
    val toIndex = all.indexOf(to)
    if (fromIndex >= 0 && toIndex >= 0) {
      val start = math.min(fromIndex, toIndex)
      val end = math.max(fromIndex, toIndex)
      selected @= all.slice(start, end + 1).toSet
    }
  }

  def clear(): Unit = {
    selected @= Set.empty
  }

  def selectAll(all: Seq[T]): Unit = {
    selected @= all.toSet
  }

  def isEmpty: Boolean = selected().isEmpty
  def nonEmpty: Boolean = selected().nonEmpty
  def size: Int = selected().size
  def contains(item: T): Boolean = selected().contains(item)
}
