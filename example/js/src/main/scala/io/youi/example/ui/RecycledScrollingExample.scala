package io.youi.example.ui

import io.youi.component.{Component, Container}
import reactify.Var

import scala.concurrent.Future

class RecycledScrollingExample {

}

class RecycledScroller[T, C <: Component](renderer: RecycledRenderer[T, C]) extends Container {
  val batchedData: Var[BatchedData[T]] = Var(BatchedData.empty[T])


}

trait RecycledRenderer[T, C <: Component] {
  def createComponent: Component
  def setData(data: T, component: C): Unit
}

trait BatchedData[T] {
  def total: Int
  def get(start: Int, end: Int): Future[List[T]]
}

object BatchedData {
  def empty[T]: BatchedData[T] = new BatchedData[T] {
    override def total: Int = 0

    override def get(start: Int, end: Int): Future[List[T]] = throw new RuntimeException("Cannot get data from empty batch")
  }

  def apply[T](data: Seq[T]): BatchedData[T] = new BatchedData[T] {
    override def total: Int = data.length

    override def get(start: Int, end: Int): Future[List[T]] = Future.successful(data.slice(start, end).toList)
  }
}