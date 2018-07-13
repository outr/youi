package io.youi

import reactify.{Val, Var}

import scala.math.Ordering

class ItemContainer[T](implicit ord: Ordering[T]) extends Iterable[T] {
  private val list = Var[List[T]](Nil)
  val items: Val[List[T]] = list

  def +=(item: T): Unit = synchronized {
    list := (item :: list()).sorted
  }
  def -=(item: T): Unit = synchronized {
    list := list().filterNot(_ == item)
  }
  def apply(): List[T] = items()

  override def iterator: Iterator[T] = items().iterator
}
