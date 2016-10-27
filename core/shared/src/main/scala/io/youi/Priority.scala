package io.youi

object Priority {
  val Fallback = Priority(0)
  val Low = Priority(100)
  val Normal = Priority(1000)
  val High = Priority(10000)
  val Critical = Priority(10000)
}

case class Priority(value: Int) extends Ordered[Priority] {
  def lower: Priority = Priority(value - 1)
  def higher: Priority = Priority(value + 1)

  override def compare(that: Priority): Int = that.value.compareTo(value)
}