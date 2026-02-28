package youi.component.recycled

import rapid.Task

trait BatchedData[T] {
  def total: Int
  def isEmpty: Boolean = total == 0
  def nonEmpty: Boolean = total > 0
  def get(start: Int, end: Int): Task[Vector[T]]
}

object BatchedData {
  def empty[T]: BatchedData[T] = new BatchedData[T] {
    override def total: Int = 0
    override def get(start: Int, end: Int): Task[Vector[T]] =
      Task.error(new RuntimeException("Cannot get data from empty batch"))
  }

  def apply[T](data: Seq[T]): BatchedData[T] = new BatchedData[T] {
    override lazy val total: Int = data.length
    override def get(start: Int, end: Int): Task[Vector[T]] =
      Task(data.slice(start, start + end).toVector)
  }
}
